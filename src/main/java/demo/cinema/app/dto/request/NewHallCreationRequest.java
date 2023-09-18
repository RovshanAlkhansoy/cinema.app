package demo.cinema.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewHallCreationRequest {

    @NotBlank(message = "Hall name is required")
    @Size(max = 255, message = "Hall name must not exceed 255 characters")
    private String hallName;

    @Positive(message = "Capacity must be positive")
    private int capacity;

}

