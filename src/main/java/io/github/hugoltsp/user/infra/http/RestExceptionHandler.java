package io.github.hugoltsp.user.infra.http;

import io.github.hugoltsp.user.infra.http.domain.ErrorResponse;
import io.github.hugoltsp.user.usecase.domain.UserApiAuthenticationException;
import io.github.hugoltsp.user.usecase.domain.UserApiException;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger;

    RestExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        logger.error("Error:", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<?> applicationException(UserApiException e) {
        logger.error("Error:", e);
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserApiAuthenticationException.class)
    public ResponseEntity<?> userApiAuthenticationException(UserApiAuthenticationException e) {
        logger.error("Error:", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        return ResponseEntity.badRequest()
                .body(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(ErrorResponse::newErrorResponse)
                        .collect(Collectors.toList()));
    }
}
