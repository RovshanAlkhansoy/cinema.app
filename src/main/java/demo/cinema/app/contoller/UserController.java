package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.UserRegisterRequest;
import demo.cinema.app.dto.response.UserRegisterResponse;
import demo.cinema.app.exception.RegistrationFailedException;
import demo.cinema.app.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserRegisterResponse> registerNewUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.registerNewUser(userRegisterRequest);
    }


}
