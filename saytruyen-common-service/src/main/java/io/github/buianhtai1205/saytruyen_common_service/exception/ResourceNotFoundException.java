package io.github.buianhtai1205.saytruyen_common_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     */
    public ResourceNotFoundException(String param1, String param2) {
        super(param1 + " with ID: " + param2 + " is not found in system!");
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
