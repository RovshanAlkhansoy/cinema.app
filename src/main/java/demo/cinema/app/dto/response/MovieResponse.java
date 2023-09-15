package demo.cinema.app.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {

    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private Date releaseDate;
    private String rating;

}