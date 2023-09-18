package demo.cinema.app.exception;

public class InvalidTicketCount extends RuntimeException {

    public InvalidTicketCount(String message) {
        super(message);
    }

}
