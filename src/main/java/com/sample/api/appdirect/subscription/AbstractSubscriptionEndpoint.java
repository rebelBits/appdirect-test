package com.sample.api.appdirect.subscription;

import java.util.Optional;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.api.appdirect.Endpoint;
import com.sample.api.appdirect.Response;
import com.sample.api.appdirect.exception.InvalidConfigurationException;
import com.sample.api.appdirect.exception.InvalidNotificationException;
import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.exception.ConflictException;

public abstract class AbstractSubscriptionEndpoint implements Endpoint<Subscription> {

    private static final String DEFAULT_RESPONSE_MESSAGE = "Operation executed successfully";

    @Override
    public Response process(final String url, final EventType eventType) {
        final Optional<AbstractNotificationHandler<Subscription>> handler = getNotificationHandler();

        if (!handler.isPresent()) {
            throw new InvalidConfigurationException("A notification handler needs to be configured");
        }

        return buildResponse(handler.get().process(url, eventType));
    }

    protected Response buildResponse(final Subscription subscription) {
        return Response.Builder.ok()
                .forAccountIdentifier(isSubscriptionAccountIdRequired() ? Long.valueOf(subscription.getAccountId()) : null)
                .withMessage(getResponseMessage())
                .build();
    }

    protected String getResponseMessage() {
        return DEFAULT_RESPONSE_MESSAGE;
    }

    protected boolean isSubscriptionAccountIdRequired() {
        return Boolean.FALSE;
    }

    @ExceptionHandler({ InvalidNotificationException.class, ConflictException.class })
    public Response handleInputNotAcceptable(final RuntimeException e) {
        return Response.Builder.fail()
                .withErrorCode("INVALID_RESPONSE")
                .withMessage(e.getMessage())
                .build();
    }
}
