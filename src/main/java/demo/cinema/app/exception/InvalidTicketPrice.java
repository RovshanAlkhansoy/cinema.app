package demo.cinema.app.exception;

public class InvalidTicketPrice extends RuntimeException {

    public InvalidTicketPrice(String message) {
        super(message);
    }

}
