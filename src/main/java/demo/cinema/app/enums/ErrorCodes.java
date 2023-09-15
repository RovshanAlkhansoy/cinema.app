package demo.cinema.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCodes {

    USER_NOT_FOUND("CINEMA-APP-TECHNICAL-001", "User not found with given username"),
    MOVIE_NOT_FOUND("CINEMA-APP-TECHNICAL-002", "Movie not found with given id"),
    YYY("ABB-SEND-SMS-MS-TECHNICAL-0006", "Sms not found with given id");


    private final String code;
    private final String message;
}
