package com.sample.api.appdirect.exception;

public class InvalidConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 6080802091782329447L;

    public InvalidConfigurationException(final String message) {
        super(message);
    }
}
