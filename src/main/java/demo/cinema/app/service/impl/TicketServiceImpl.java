package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.exception.TicketNotFound;
import demo.cinema.app.mapper.TicketMapper;
import demo.cinema.app.model.Ticket;
import demo.cinema.app.repository.TicketRepository;
import demo.cinema.app.service.TicketService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketResponse createTicket(NewTicketCreationRequest newTicketCreationRequest) {
        Ticket savedTicket = ticketRepository.save(ticketMapper.fromTicketCreateRequestToTicket(newTicketCreationRequest));
        return ticketMapper.fromTicketToResponse(savedTicket);
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
                .collect(Collectors.toList());
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
