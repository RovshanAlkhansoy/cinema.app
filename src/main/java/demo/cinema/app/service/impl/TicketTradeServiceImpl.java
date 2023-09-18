package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.TicketBookingRequest;
import demo.cinema.app.dto.request.TicketReversalRequest;
import demo.cinema.app.dto.response.TicketBookingResponse;
import demo.cinema.app.dto.response.TicketReversalResponse;
import demo.cinema.app.enums.TicketStatus;
import demo.cinema.app.exception.InsufficientBalance;
import demo.cinema.app.exception.InvalidTicketCount;
import demo.cinema.app.exception.SessionNotFound;
import demo.cinema.app.exception.TicketNotFound;
import demo.cinema.app.exception.UserNotFound;
import demo.cinema.app.model.Session;
import demo.cinema.app.model.Ticket;
import demo.cinema.app.model.User;
import demo.cinema.app.repository.SessionRepository;
import demo.cinema.app.repository.TicketRepository;
import demo.cinema.app.repository.UserRepository;
import demo.cinema.app.service.TicketTradeService;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketTradeServiceImpl implements TicketTradeService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;


    @Override
    public TicketBookingResponse bookTicket(TicketBookingRequest ticketBookingRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        Optional<Session> session = Optional.ofNullable(sessionRepository.findById(ticketBookingRequest.getSessionId())
                .orElseThrow(SessionNotFound::new));

        int requiredTicketCount = ticketBookingRequest.getRequiredTicketCount();
        int availableSeatsCount = session.get().getAvailableSeatsCount();

        if (requiredTicketCount <= 0 || requiredTicketCount > availableSeatsCount) {
            throw new InvalidTicketCount("Invalid ticket count");
        }

        BigDecimal ticketPrice = ticketRepository.findTicketPriceBySessionId(ticketBookingRequest.getSessionId());
        BigDecimal totalPrice = ticketPrice.multiply(BigDecimal.valueOf(requiredTicketCount));

        if (user.getBalance().compareTo(totalPrice) < 0) {
            throw new InsufficientBalance("Insufficient balance to book tickets");
        }

        List<Ticket> tickets = IntStream.range(0, requiredTicketCount)
                .mapToObj(i -> Ticket.builder()
                        .ticketPrice(ticketPrice)
                        .ticketStatus(TicketStatus.BOOKED)
                        .bookingDateTime(Date.from(Instant.now()))
                        .session(session.get())
                        .user(user)
                        .build())
                .toList();


        List<Long> ticketIds = ticketRepository.saveAll(tickets).stream()
                .map(Ticket::getId)
                .toList();

        BigDecimal newBalance = user.getBalance().subtract(totalPrice);
        user.setBalance(newBalance);
        userRepository.save(user);

        return TicketBookingResponse.builder()
                .ticketId(ticketIds)
                .ticketPrice(ticketPrice)
                .ticketStatus(TicketStatus.BOOKED)
                .bookingDateTime(new Date())
                .sessionId(session.get().getId())
                .userId(user.getId())
                .build();
    }

    @Override
    public TicketReversalResponse reverseTicket(TicketReversalRequest ticketReversalRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        List<Ticket> ticketsToReverse = ticketRepository.findTicketsToReverse(
                ticketReversalRequest.getSessionId(),
                userId
        );

        if (ticketsToReverse.isEmpty()) {
            throw new TicketNotFound("No tickets found for reversal");
        }

        BigDecimal totalRefundAmount = calculateTotalRefundAmount(ticketsToReverse);

        BigDecimal newBalance = user.getBalance().add(totalRefundAmount);
        user.setBalance(newBalance);
        userRepository.save(user);

        ticketsToReverse.forEach(ticket -> ticket.setTicketStatus(TicketStatus.AVAILABLE));
        ticketRepository.saveAll(ticketsToReverse);

        List<Long> reversedTicketIds = ticketsToReverse.stream()
                .map(Ticket::getId)
                .toList();

        return TicketReversalResponse.builder()
                .reversedTicketIds(reversedTicketIds)
                .refundAmount(totalRefundAmount)
                .userId(user.getId())
                .build();
    }


    private BigDecimal calculateTotalRefundAmount(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getTicketPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
