package demo.cinema.app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
public class UpdateMovieRequest {

    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 255, message = "Genre must not exceed 255 characters")
    private String genre;

    private Integer duration;

    @Past(message = "Release date must be in the past")
    private Date releaseDate;

    @Size(max = 10, message = "Rating must not exceed 10 characters")
    private String rating;

    @NotNull(message = "Session type is required")
    private List<String> sessionTypes;


}
