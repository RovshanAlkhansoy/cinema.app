package demo.cinema.app.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketReversalResponse {

    private List<Long> reversedTicketIds;
    private BigDecimal refundAmount;
    private Long userId;

}
