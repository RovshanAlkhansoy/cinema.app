package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class RefreshTokenNotFound extends NotFoundException {

    public RefreshTokenNotFound(String... args) {
        super(ErrorCodes.REFRESH_TOKEN_NOT_FOUND.getCode(), ErrorCodes.REFRESH_TOKEN_NOT_FOUND.getMessage(), args);
    }


}
