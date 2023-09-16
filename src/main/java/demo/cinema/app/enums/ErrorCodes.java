package demo.cinema.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCodes {

    USER_NOT_FOUND("CINEMA-APP-TECHNICAL-001", "User not found with given username"),
    MOVIE_NOT_FOUND("CINEMA-APP-TECHNICAL-002", "Movie not found with given id"),
    TICKET_NOT_FOUND("CINEMA-APP-TECHNICAL-003", "Ticket not found with given id"),
    SESSION_NOT_FOUND("CINEMA-APP-TECHNICAL-004", "Session not found with given id"),
    HALL_NOT_FOUND("CINEMA-APP-TECHNICAL-004", "Hall not found with given id");


    private final String code;
    private final String message;
}
