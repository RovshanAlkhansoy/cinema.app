package demo.cinema.app.contoller;

import demo.cinema.app.dto.response.BuyTicketResponse;
import demo.cinema.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping(value = "/buy")
    public ResponseEntity<BuyTicketResponse> uyTicket(@RequestParam Long ticketId,
                                                      @RequestParam Long userId,
                                                      @RequestParam int quantity) {
        // Call the buyTicket method in the service
        // Return appropriate responses based on success or failure
        return null;
    }


}
