package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String code, String message, String... args) {
        super(ErrorCodes.USER_NOT_FOUND.getCode(), ErrorCodes.USER_NOT_FOUND.getMessage(), args);
    }

}
