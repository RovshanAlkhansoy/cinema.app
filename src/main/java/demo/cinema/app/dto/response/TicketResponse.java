package demo.cinema.app.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private Long ticketId;
    private Double ticketPrice;
    private LocalDateTime bookingDateTime;
    private LocalDateTime reversedDateTime;
    private Long sessionId;
    private Long userId;
}
