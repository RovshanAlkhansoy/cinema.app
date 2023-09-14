package demo.cinema.app.authentication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {

    @NotBlank(message = "FirstName can not be empty, blank or null")
    private String firstName;

    @NotBlank(message = "LastName can not be empty, blank or null")
    private String lastName;

    @NotBlank(message = "FatherName can not be empty, blank or null")
    private String fatherName;

    @NotBlank(message = "UserName can not be empty, blank or null")
    private String userName;

    @NotBlank(message = "Password can not be empty, blank or null")
    private String password;

}
