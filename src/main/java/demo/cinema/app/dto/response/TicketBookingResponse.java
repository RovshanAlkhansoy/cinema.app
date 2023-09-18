package demo.cinema.app.dto.response;

import demo.cinema.app.enums.TicketStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBookingResponse {

    private List<Long> ticketId;
    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;
    private Date bookingDateTime;
    private Long sessionId;
    private Long userId;

}
