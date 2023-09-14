package demo.cinema.app.service;

import demo.cinema.app.dto.request.UserRegisterRequest;
import demo.cinema.app.dto.response.UserRegisterResponse;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Optional<UserRegisterResponse> registerNewUser(UserRegisterRequest userRegisterRequest);

}
