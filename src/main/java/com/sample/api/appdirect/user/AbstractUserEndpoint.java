package com.sample.api.appdirect.user;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sample.api.appdirect.Endpoint;
import com.sample.api.appdirect.Response;
import com.sample.api.appdirect.exception.InvalidConfigurationException;
import com.sample.api.appdirect.exception.InvalidNotificationException;
import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.User;
import com.sample.data.dao.exception.ConflictException;
import com.sample.data.dao.exception.NotFoundException;

public abstract class AbstractUserEndpoint implements Endpoint<User> {

    private static final String DEFAULT_RESPONSE_MESSAGE = "Operation executed successfully";

    @Override
    public Response process(final String url, final EventType eventType) {
        final Optional<AbstractNotificationHandler<User>> handler = getNotificationHandler();

        if (!handler.isPresent()) {
            throw new InvalidConfigurationException("A notification handler needs to be configured");
        }

        return buildResponse(handler.get().process(url, eventType));
    }

    protected Response buildResponse(final User user) {
        return Response.Builder.ok()
                .withMessage(getResponseMessage())
                .build();
    }

    protected String getResponseMessage() {
        return DEFAULT_RESPONSE_MESSAGE;
    }

    @ExceptionHandler(InvalidNotificationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response handleInputNotAcceptable(final RuntimeException e) {
        return Response.Builder.fail()
                .withErrorCode("INVALID_RESPONSE")
                .withMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response handleConflict(final ConflictException e) {
        return Response.Builder.fail()
                .withErrorCode("USER_ALREADY_EXISTS")
                .withMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleUserNotFound(final NotFoundException e) {
        return Response.Builder.fail()
                .withErrorCode("USER_NOT_FOUND")
                .withMessage(e.getMessage())
                .build();
    }

}
