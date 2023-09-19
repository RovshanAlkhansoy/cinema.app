package demo.cinema.app.authentication.controller;

import demo.cinema.app.authentication.dto.request.RefreshTokenRequest;
import demo.cinema.app.authentication.dto.request.UserAuthenticationRequest;
import demo.cinema.app.authentication.dto.request.UserRegisterRequest;
import demo.cinema.app.authentication.dto.response.JwtResponse;
import demo.cinema.app.authentication.dto.response.UserRegisterResponse;
import demo.cinema.app.authentication.model.RefreshToken;
import demo.cinema.app.authentication.service.AuthenticateService;
import demo.cinema.app.authentication.service.RefreshTokenService;
import demo.cinema.app.exception.RefreshTokenNotFound;
import demo.cinema.app.authentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserRegisterResponse> registerNewUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(authenticateService.register(userRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody UserAuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = refreshTokenService.createRefreshToken(authenticationRequest.getUserName());

        return ResponseEntity.ok(JwtResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(userDetailsService.loadUserByUsername(user.getUsername()));
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(RefreshTokenNotFound::new);
    }

}
