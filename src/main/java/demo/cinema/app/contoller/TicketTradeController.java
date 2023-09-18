package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.TicketBookingRequest;
import demo.cinema.app.dto.request.TicketReversalRequest;
import demo.cinema.app.dto.response.TicketBookingResponse;
import demo.cinema.app.dto.response.TicketReversalResponse;
import demo.cinema.app.model.User;
import demo.cinema.app.service.TicketTradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Api(tags = "Ticket Trade")
public class TicketTradeController {

    private final TicketTradeService ticketTradeService;

    @PostMapping("/book-ticket")
    @ApiOperation("Book a ticket")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket booked successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 403, message = "Access forbidden")
    })
    public ResponseEntity<TicketBookingResponse> bookTicket(@RequestBody TicketBookingRequest ticketBookingRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();
        TicketBookingResponse bookedTicket = ticketTradeService.bookTicket(ticketBookingRequest, userId);
        return ResponseEntity.ok(bookedTicket);
    }

    @PostMapping("/reverse-ticket")
    @ApiOperation("Reverse a ticket booking")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket reversed successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 403, message = "Access forbidden")
    })
    public ResponseEntity<TicketReversalResponse> reverseTicket(
            @RequestBody TicketReversalRequest ticketReversalRequest,
            @PathVariable Long userId) {
        TicketReversalResponse reversedTicket = ticketTradeService.reverseTicket(ticketReversalRequest, userId);
        return ResponseEntity.ok(reversedTicket);
    }
}
