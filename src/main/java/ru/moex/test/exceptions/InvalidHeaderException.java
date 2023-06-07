package ru.moex.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid valid header exception
 */
@ResponseStatus(value= HttpStatus.NOT_IMPLEMENTED, reason="Неверное значение заголовка x-Source")
public class InvalidHeaderException extends RuntimeException {

    public InvalidHeaderException(String message) {
        super(message);
    }
}
