package demo.cinema.app.authentication.service.impl;

import demo.cinema.app.authentication.model.RefreshToken;
import demo.cinema.app.authentication.service.RefreshTokenService;
import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.RefreshTokenExpired;
import demo.cinema.app.exception.UserNotFound;
import demo.cinema.app.model.User;
import demo.cinema.app.repository.RefreshTokenRepository;
import demo.cinema.app.repository.UserRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final UserRepository userRepository;
    private RefreshTokenRepository refreshTokenRepository;


    public RefreshToken createRefreshToken(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (userOptional.isPresent()) {
            RefreshToken refreshToken = RefreshToken.builder()
                    .user(userOptional.get())
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(180000))
                    .build();
            return refreshTokenRepository.save(refreshToken);
        } else {
            throw new UserNotFound(ErrorCodes.USER_NOT_FOUND.getCode(), ErrorCodes.USER_NOT_FOUND.getMessage());
        }
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpired(token.getToken() + " Refresh token was expired. Please make a new signIn " +
                    "request");
        }
        return token;
    }

}
