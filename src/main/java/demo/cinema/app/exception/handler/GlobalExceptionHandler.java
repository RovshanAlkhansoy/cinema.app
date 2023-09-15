package demo.cinema.app.exception.handler;

import demo.cinema.app.dto.response.ErrorResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final String LOCALIZED_MESSAGE_PREFIX = "error.";

    private final MessageSource messageSource;


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
