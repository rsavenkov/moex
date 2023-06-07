package ru.moex.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Field validation exception
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Валидация обязательных полей прошла не успешно")
public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String message) {
        super(message);
    }
}
