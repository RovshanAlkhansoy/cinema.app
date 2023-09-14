package demo.cinema.app.authentication.controller;

import demo.cinema.app.authentication.dto.request.UserAuthenticationRequest;
import demo.cinema.app.authentication.dto.request.UserRegisterRequest;
import demo.cinema.app.authentication.dto.response.AuthenticationResponse;
import demo.cinema.app.authentication.dto.response.UserRegisterResponse;
import demo.cinema.app.authentication.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserRegisterResponse> registerNewUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(authenticateService.register(userRegisterRequest));
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody UserAuthenticationRequest userAuthenticationRequest) {
        return ResponseEntity.ok(authenticateService.authenticate(userAuthenticationRequest));
    }


}
