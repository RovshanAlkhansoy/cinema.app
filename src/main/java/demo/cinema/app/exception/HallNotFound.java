package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class HallNotFound extends NotFoundException {

    public HallNotFound(String... args) {
        super(ErrorCodes.HALL_NOT_FOUND.getCode(), ErrorCodes.HALL_NOT_FOUND.getMessage(), args);
    }

}
