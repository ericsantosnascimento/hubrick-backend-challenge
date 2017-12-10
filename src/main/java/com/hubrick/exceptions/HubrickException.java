package com.hubrick.exceptions;

public class HubrickException extends RuntimeException {

    public HubrickException(String message) {
        super(message);
    }

    public HubrickException(String message, Throwable cause) {
        super(message, cause);
    }

}
