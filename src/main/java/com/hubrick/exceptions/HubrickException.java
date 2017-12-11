package com.hubrick.exceptions;

/**
 * More generalist business exception
 *
 * @author eric.nascimento
 */
public class HubrickException extends RuntimeException {

    public HubrickException(String message) {
        super(message);
    }

    public HubrickException(String message, Throwable cause) {
        super(message, cause);
    }

}
