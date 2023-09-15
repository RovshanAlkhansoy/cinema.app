package demo.cinema.app.exception;

import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;

public class TicketNotFound extends NotFoundException {

    public TicketNotFound(String... args) {
        super(ErrorCodes.TICKET_NOT_FOUND.getCode(), ErrorCodes.TICKET_NOT_FOUND.getMessage(), args);
    }

}
