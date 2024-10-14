package io.github.buianhtai1205.saytruyen_common_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Null exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullException extends RuntimeException {
    /**
     * Instantiates a new Null exception.
     *
     * @param param the param
     */
    public NullException(String param) {
        super(param + " is NULL value! Please check data input!");
    }
}
