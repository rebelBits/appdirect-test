package com.sample.api.appdirect.exception;

public class InvalidNotificationException extends RuntimeException {

    private static final long serialVersionUID = 7394750461224595926L;

    public InvalidNotificationException(final String message) {
        super(message);
    }

    public InvalidNotificationException(final Throwable cause) {
        super(cause);
    }
}
