package demo.cinema.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCodes {

    INTERNAL_SERVER_ERROR("CINEMA-APP-INTERNAL-001", "Internal server error!"),
    VALIDATION_ERROR("CINEMA-APP-VALIDATION-001", "Validation error!"),
    USER_NOT_FOUND("CINEMA-APP-TECHNICAL-001", "User not found with given username"),
    MOVIE_NOT_FOUND("CINEMA-APP-TECHNICAL-002", "Movie not found with given id"),
    TICKET_NOT_FOUND("CINEMA-APP-TECHNICAL-003", "Ticket not found with given id"),
    SESSION_NOT_FOUND("CINEMA-APP-TECHNICAL-004", "Session not found with given id"),
    HALL_NOT_FOUND("CINEMA-APP-TECHNICAL-005", "Hall not found with given id"),
    REFRESH_TOKEN_NOT_FOUND("CINEMA-APP-TECHNICAL-006", "Refresh token not found in db");


    private final String code;
    private final String message;
}
