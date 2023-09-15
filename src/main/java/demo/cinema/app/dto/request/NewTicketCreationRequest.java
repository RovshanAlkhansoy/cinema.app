package demo.cinema.app.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewTicketCreationRequest {

    @NotNull(message = "Ticket price is required")
    private BigDecimal ticketPrice;

    @NotNull(message = "Session ID is required")
    private Long sessionId;

    @NotNull(message = "User ID is required")
    private Long userId;

}


