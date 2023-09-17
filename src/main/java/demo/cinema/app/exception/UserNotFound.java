package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class UserNotFound extends NotFoundException {

    public UserNotFound(String... args) {
        super(ErrorCodes.USER_NOT_FOUND.getCode(), ErrorCodes.USER_NOT_FOUND.getMessage(), args);
    }

}
