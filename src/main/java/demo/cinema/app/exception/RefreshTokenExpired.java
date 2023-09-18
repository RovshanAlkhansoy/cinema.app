package demo.cinema.app.exception;

public class RefreshTokenExpired extends RuntimeException {

    public RefreshTokenExpired(String message) {
        super(message);
    }

}
