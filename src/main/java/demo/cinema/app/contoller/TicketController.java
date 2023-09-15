package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.BuyTicketResponse;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.model.Ticket;
import demo.cinema.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<TicketResponse> createTicket(@RequestBody NewTicketCreationRequest request) {
//        Ticket createdTicket = ticketService.createTicket(request);
//        return ResponseEntity.ok(mapToResponse(createdTicket));
//    }
//
//    @GetMapping("/{ticketId}")
//    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long ticketId) {
//        Ticket ticket = ticketService.getTicketById(ticketId);
//        return ResponseEntity.ok(mapToResponse(ticket));
//    }
//
//    @PutMapping("/{ticketId}")
//    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
//        Ticket updatedTicket = ticketService.updateTicket(ticketId, request);
//        return ResponseEntity.ok(mapToResponse(updatedTicket));
//    }
//
//    @DeleteMapping("/{ticketId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteTicket(@PathVariable Long ticketId) {
//        ticketService.deleteTicket(ticketId);
//    }

//    private TicketResponse mapToResponse(Ticket ticket) {
//        return TicketResponse.builder()
//                .ticketId(ticket.getTicketId())
//                .ticketPrice(ticket.getTicketPrice())
//                .bookingDateTime(ticket.getBookingDateTime())
//                .reversedDateTime(ticket.getReversedDateTime())
//                .sessionId(ticket.getSession().getSessionId())
//                .userId(ticket.getUser().getUserId())
//                .build();
//    }
}
