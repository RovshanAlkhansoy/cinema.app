package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.enums.TicketStatus;
import demo.cinema.app.exception.InvalidTicketPrice;
import demo.cinema.app.exception.SessionNotFound;
import demo.cinema.app.exception.TicketCreationException;
import demo.cinema.app.exception.TicketNotFound;
import demo.cinema.app.mapper.TicketMapper;
import demo.cinema.app.model.Session;
import demo.cinema.app.model.Ticket;
import demo.cinema.app.repository.SessionRepository;
import demo.cinema.app.repository.TicketRepository;
import demo.cinema.app.service.TicketService;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketResponse createTicket(NewTicketCreationRequest newTicketCreationRequest) {
        try {
            if (newTicketCreationRequest.getTicketPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidTicketPrice("Ticket price must be greater than zero.");
            }

            Session session = sessionRepository.findById(newTicketCreationRequest.getSessionId())
                    .orElseThrow(SessionNotFound::new);

            Ticket ticket = Ticket.builder()
                    .ticketPrice(newTicketCreationRequest.getTicketPrice())
                    .ticketStatus(TicketStatus.BOOKED)
                    .bookingDateTime(Date.from(Instant.now()))
                    .session(session)
                    .build();

            Ticket savedTicket = ticketRepository.save(ticket);
            return ticketMapper.fromTicketToResponse(savedTicket);
        } catch (Exception e) {
            throw new TicketCreationException("Failed to create a ticket");
        }
    }

    @Override
    public TicketResponse updateTicket(Long ticketId, UpdateTicketRequest updateTicketRequest) {
        return ticketRepository.findById(ticketId)
                .map(ticket -> {
                    ticket.setTicketPrice(updateTicketRequest.getTicketPrice());
                    Ticket updatedTicket = ticketRepository.save(ticket);
                    return ticketMapper.fromTicketToResponse(updatedTicket);
                })
                .orElseThrow(TicketNotFound::new);
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::fromTicketToResponse)
                .orElseThrow(TicketNotFound::new);
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::fromTicketToResponse)
                .toList();
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

}
