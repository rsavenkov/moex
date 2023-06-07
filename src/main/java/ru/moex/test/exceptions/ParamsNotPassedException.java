package ru.moex.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Params not passed exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ни один из параметров не передан!")
public class ParamsNotPassedException extends RuntimeException {

}
