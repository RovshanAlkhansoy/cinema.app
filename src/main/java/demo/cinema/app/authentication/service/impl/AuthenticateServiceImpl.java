package demo.cinema.app.authentication.service.impl;

import demo.cinema.app.authentication.dto.request.UserAuthenticationRequest;
import demo.cinema.app.authentication.dto.request.UserRegisterRequest;
import demo.cinema.app.authentication.dto.response.AuthenticationResponse;
import demo.cinema.app.authentication.dto.response.UserRegisterResponse;
import demo.cinema.app.authentication.service.AuthenticateService;
import demo.cinema.app.enums.Role;
import demo.cinema.app.model.User;
import demo.cinema.app.repository.UserRepository;
import demo.cinema.app.authentication.service.JwtService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        var user = User.builder()
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .fatherName(userRegisterRequest.getFatherName())
                .userName(userRegisterRequest.getUserName())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .balance(BigDecimal.valueOf(100.00))
                .role(roleCreate())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return UserRegisterResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthenticationRequest.getUserName(),
                        userAuthenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByUserName(userAuthenticationRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with given username: " +
                        userAuthenticationRequest.getUserName()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    private Role roleCreate() {
        long numberOfUsers = userRepository.count();
        return numberOfUsers < 2 ? Role.ADMIN : Role.USER;
    }

}
