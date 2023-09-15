package demo.cinema.app.service;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.TicketResponse;
import java.util.List;

public interface TicketService {

    TicketResponse createTicket(NewTicketCreationRequest newTicketCreationRequest);

    TicketResponse updateTicket(Long ticketId, UpdateTicketRequest updateTicketRequest);

    TicketResponse getTicketById(Long id);

    List<TicketResponse> getAllTickets();

    void deleteTicketById(Long ticketId);

    void deleteAllTickets();

}
