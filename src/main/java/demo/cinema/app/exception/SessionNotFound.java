package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class SessionNotFound extends NotFoundException {

    public SessionNotFound(String... args) {
        super(ErrorCodes.SESSION_NOT_FOUND.getCode(), ErrorCodes.SESSION_NOT_FOUND.getMessage(), args);
    }

}
