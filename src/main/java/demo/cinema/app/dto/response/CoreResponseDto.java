package demo.cinema.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.cinema.app.constants.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoreResponseDto<T> {

    private T data;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String message;

    public static <T> CoreResponseDto<T> success(T data) {
        return new CoreResponseDto<>(data, MessageConstants.SUCCESS);
    }

}