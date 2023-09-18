package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.request.UpdateTicketRequest;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "Tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/createTicket")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new ticket")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ticket created successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<TicketResponse> createTicket(@RequestBody NewTicketCreationRequest request) {
        TicketResponse createdTicket = ticketService.createTicket(request);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/updateTicket/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Update a ticket by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
        TicketResponse updatedTicket = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/getTicketById/{ticketId}")
    @ApiOperation("Get a ticket by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket retrieved successfully"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long ticketId) {
        TicketResponse ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/getAllTickets")
    @ApiOperation("Get all tickets")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tickets retrieved successfully")
    })
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/deleteTicketById/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a ticket by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Ticket deleted successfully"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    public void deleteTicketById(@PathVariable Long ticketId) {
        ticketService.deleteTicketById(ticketId);
    }

    @DeleteMapping("/deleteAllTickets")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete all tickets")
    @ApiResponses({
            @ApiResponse(code = 204, message = "All tickets deleted successfully")
    })
    public void deleteAllTickets() {
        ticketService.deleteAllTickets();
    }
}
