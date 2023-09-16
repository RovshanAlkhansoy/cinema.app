package demo.cinema.app.dto.response;

import demo.cinema.app.enums.SessionType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResponse {

    private Long id;
    private int availableSeatsCount;
    private Date showtime;
    private SessionType sessionType;
    private Long hallId;
    private Long movieId;

}