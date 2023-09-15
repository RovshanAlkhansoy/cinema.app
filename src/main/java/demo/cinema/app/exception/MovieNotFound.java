package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class MovieNotFound extends NotFoundException {

    public MovieNotFound(String... args) {
        super(ErrorCodes.MOVIE_NOT_FOUND.getCode(), ErrorCodes.MOVIE_NOT_FOUND.getMessage(), args);
    }

}
