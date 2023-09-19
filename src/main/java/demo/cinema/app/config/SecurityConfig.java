package demo.cinema.app.config;

import demo.cinema.app.authentication.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("cinema-app/v1/api/register")
                .permitAll()
                .requestMatchers("cinema-app/v1/api/halls/create",
                        "cinema-app/v1/api/halls/getHallById/{id}",
                        "cinema-app/v1/api/halls/getAllHalls",
                        "cinema-app/v1/api/halls/updateHall/{id}",
                        "cinema-app/v1/api/halls/deleteHallById/{id}",
                        "cinema-app/v1/api/movies/create" ,
                        "cinema-app/v1/api/movies/updateMovie/{id}" ,
                        "cinema-app/v1/api/movies/all-with-sessions" ,
                        "cinema-app/v1/api/movies/getAllMovies" ,
                        "cinema-app/v1/api/movies/getMovieById/{id}" ,
                        "cinema-app/v1/api/movies/deleteMovieById/{id}" ,
                        "cinema-app/v1/api/movies/deleteAllMovies" ,
                        "cinema-app/v1/api/sessions/createSession" ,
                        "cinema-app/v1/api/sessions/by-movie/{movieId}" ,
                        "cinema-app/v1/api/sessions/by-date/{from}/{to}" ,
                        "cinema-app/v1/api/sessions/by-hall/{hallId}" ,
                        "cinema-app/v1/api/sessions/by-movie-and-date" ,
                        "cinema-app/v1/api/sessions/by-movie-and-hall" ,
                        "cinema-app/v1/api/sessions/available-seats/{sessionId}" ,
                        "cinema-app/v1/api/sessions/getSessionById/{sessionId}" ,
                        "cinema-app/v1/api/sessions/getAllSessions" ,
                        "cinema-app/v1/api/sessions/updateSession/{sessionId}" ,
                        "cinema-app/v1/api/sessions/deleteSessionById/{sessionId}" ,
                        "cinema-app/v1/api/sessions/deleteAllSessions" ,
                        "cinema-app/v1/api/tickets/createTicket" ,
                        "cinema-app/v1/api/tickets/updateTicket/{ticketId}" ,
                        "cinema-app/v1/api/tickets/getTicketById/{ticketId}" ,
                        "cinema-app/v1/api/tickets/getAllTickets" ,
                        "cinema-app/v1/api/tickets/deleteTicketById/{ticketId}" ,
                        "cinema-app/v1/api/tickets/deleteAllTickets" ,
                        "cinema-app/v1/api/tickets/book-ticket" ,
                        "cinema-app/v1/api/tickets/reverse-ticket")
                .hasAuthority("ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("cinema-app/v1/api/movies/all-with-sessions" ,
                        "cinema-app/v1/api/movies/getAllMovies" ,
                        "cinema-app/v1/api/sessions/by-movie/{movieId}" ,
                        "cinema-app/v1/api/sessions/by-date/{from}/{to}" ,
                        "cinema-app/v1/api/sessions/by-hall/{hallId}" ,
                        "cinema-app/v1/api/sessions/by-movie-and-date" ,
                        "cinema-app/v1/api/sessions/by-movie-and-hall" ,
                        "cinema-app/v1/api/sessions/available-seats/{sessionId}" ,
                        "cinema-app/v1/api/sessions/getSessionById/{sessionId}" ,
                        "cinema-app/v1/api/sessions/getAllSessions" ,
                        "cinema-app/v1/api/tickets/book-ticket" ,
                        "cinema-app/v1/api/tickets/reverse-ticket" ,
                        "cinema-app/v1/api/tickets/getTicketById/{ticketId}" ,
                        "cinema-app/v1/api/tickets/getAllTickets")
                .hasRole("USER")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
