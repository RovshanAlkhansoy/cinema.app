package demo.cinema.app.dto.request;

import demo.cinema.app.enums.SessionType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewSessionCreationRequest {

    @NotNull(message = "Movie ID is required")
    @Positive(message = "Movie ID must be positive")
    private Long movieId;

    @NotNull(message = "Hall ID is required")
    @Positive(message = "Hall ID must be positive")
    private Long hallId;

    @NotNull(message = "Session type is required")
    private SessionType sessionType;

    @NotNull(message = "Showtime is required")
    @Future(message = "Showtime must be in the future")
    private Date showtime;

    @NotNull(message = "Available seats count is required")
    @Positive(message = "Available seats count must be positive")
    @Max(value = 30,message = "Each Session can have maximum 30 seats")
    private Integer availableSeatsCount;
}
