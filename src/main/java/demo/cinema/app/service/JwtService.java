package demo.cinema.app.service;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);
    <T> T extractClaim(String token, Function<Claims,T> claimsResolver);
    String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

}
