package demo.cinema.app.exception.handler;

import demo.cinema.app.dto.response.ErrorResponse;
import demo.cinema.app.enums.ErrorCodes;
import demo.cinema.app.exception.base.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.MissingRequestValueException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final String LOCALIZED_MESSAGE_PREFIX = "error.";

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, ServerWebExchange exchange, Locale locale) {
        log.error("Occurred internal server exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), locale);
        return ofType(HttpStatus.INTERNAL_SERVER_ERROR, exchange, ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException ex, ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred not found exception {ex: {}, args: {}}", ex.getMessage(), ex.getArgs(), ex);
        String message = getLocalizedMessage(ex.getCode(), locale);
        return ofType(HttpStatus.NOT_FOUND, exchange, ex.getCode(), message);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred constraint violation exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred method arguments type mismatch exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodNotAllowedException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred method not allowed exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }


    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<ErrorResponse> handleException(MissingRequestValueException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred missing request value exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<ErrorResponse> handleException(UnsupportedMediaTypeStatusException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred unsupported media type status exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred http message not readable exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred method argument are not valid exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleException(WebExchangeBindException ex,
                                                         ServerWebExchange exchange,
                                                         Locale locale) {
        log.error("Occurred web exchange bind exception {ex: {}}", ex.getMessage(), ex);
        String message = getLocalizedMessage(ErrorCodes.VALIDATION_ERROR.getCode(), locale);
        return ofType(HttpStatus.BAD_REQUEST, exchange, ErrorCodes.VALIDATION_ERROR.getCode(), message);
    }


    private ResponseEntity<demo.cinema.app.dto.response.ErrorResponse> ofType(HttpStatus status, ServerWebExchange exchange, String code,
                                                                              String message) {
        var error = ErrorResponse.builder()
                .code(code)
                .status(status.value())
                .message(message)
                .path(exchange.getRequest().getPath().value())
                .timestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
        return new ResponseEntity<>(error, status);
    }

    private String getLocalizedMessage(String message, Locale locale) {
        return messageSource.getMessage(LOCALIZED_MESSAGE_PREFIX + message, null, message, locale);
    }

}
