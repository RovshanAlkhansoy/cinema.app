package demo.cinema.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMovieCreationRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Genre is required")
    @Size(max = 255, message = "Genre must not exceed 255 characters")
    private String genre;

    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotNull(message = "Release date is required")
    @Past(message = "Release date must be in the past")
    private Date releaseDate;

    @NotBlank(message = "Rating is required")
    @Size(max = 10, message = "Rating must not exceed 10 characters")
    private String rating;

}
