package demo.cinema.app.exception;

public class SeatsOutOfRange extends RuntimeException {


    public SeatsOutOfRange(String message) {
        super(message);
    }

}
