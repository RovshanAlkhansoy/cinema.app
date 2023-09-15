package demo.cinema.app.authentication.controller;

import demo.cinema.app.authentication.dto.request.RefreshTokenRequest;
import demo.cinema.app.authentication.dto.request.UserAuthenticationRequest;
import demo.cinema.app.authentication.dto.request.UserRegisterRequest;
import demo.cinema.app.authentication.dto.response.AuthenticationResponse;
import demo.cinema.app.authentication.dto.response.JwtResponse;
import demo.cinema.app.authentication.dto.response.UserRegisterResponse;
import demo.cinema.app.authentication.model.RefreshToken;
import demo.cinema.app.authentication.service.AuthenticateService;
import demo.cinema.app.authentication.service.RefreshTokenService;
import demo.cinema.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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


    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody UserAuthenticationRequest userAuthenticationRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticationRequest.getUserName(), userAuthenticationRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userAuthenticationRequest.getUserName());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(userAuthenticationRequest.getUserName()))
                    .token(refreshToken.getToken()).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getUsername());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }

}
