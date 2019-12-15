package io.github.hugoltsp.user.infra.http.domain;

import org.springframework.validation.FieldError;

public class ErrorResponse {

    private final String field;
    private final String message;

    public ErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this(null, message);
    }

    public static ErrorResponse newErrorResponse(FieldError fieldError) {

        return new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage());
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
