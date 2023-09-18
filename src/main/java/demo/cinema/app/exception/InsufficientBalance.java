package demo.cinema.app.exception;

public class InsufficientBalance extends RuntimeException {

    public InsufficientBalance(String message) {
        super(message);
    }

}
