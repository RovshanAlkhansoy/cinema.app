package demo.cinema.app.dto.response;

import java.math.BigDecimal;
import java.util.Date;
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
    private BigDecimal ticketPrice;
    private Date bookingDateTime;
    private Date reversedDateTime;
    private Long sessionId;
    private Long userId;

}
