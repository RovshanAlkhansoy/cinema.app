package demo.cinema.app.authentication.service;

import demo.cinema.app.authentication.dto.request.UserAuthenticationRequest;
import demo.cinema.app.authentication.dto.request.UserRegisterRequest;
import demo.cinema.app.authentication.dto.response.AuthenticationResponse;
import demo.cinema.app.authentication.dto.response.UserRegisterResponse;

public interface AuthenticateService {

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    AuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest);

}
