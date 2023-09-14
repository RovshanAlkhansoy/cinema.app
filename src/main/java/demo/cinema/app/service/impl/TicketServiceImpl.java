package demo.cinema.app.service.impl;

import demo.cinema.app.model.Ticket;
import demo.cinema.app.repository.TicketRepository;
import demo.cinema.app.repository.UserRepository;
import demo.cinema.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public Ticket buyTicket(Long ticketId, Long userId, int quantity) {
        // Logic to validate user, ticket, and quantity
        // Check if the user has enough balance
        // Deduct the balance, update the ticket quantity, and save changes
        return null;
    }

}
