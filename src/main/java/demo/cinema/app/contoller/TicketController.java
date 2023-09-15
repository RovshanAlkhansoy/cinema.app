package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.service.TicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResponse> createTicket(@RequestBody NewTicketCreationRequest request) {
        TicketResponse createdTicket = ticketService.createTicket(request);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
        TicketResponse updatedTicket = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long ticketId) {
        TicketResponse ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> ticket = ticketService.getAllTickets();
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicketById(ticketId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket() {
        ticketService.deleteAllTickets();
    }

}
