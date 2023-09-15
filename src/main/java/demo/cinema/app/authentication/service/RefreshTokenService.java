package demo.cinema.app.authentication.service;

import demo.cinema.app.authentication.model.RefreshToken;
import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String username);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

}
