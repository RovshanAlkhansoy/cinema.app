package demo.cinema.app.service;

import io.jsonwebtoken.Claims;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(String userName);

    boolean isTokenValid(String token, UserDetails userDetails);

}
