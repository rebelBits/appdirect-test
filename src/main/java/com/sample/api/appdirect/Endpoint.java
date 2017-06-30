package com.sample.api.appdirect;

import java.util.Optional;

import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.EventType;

public interface Endpoint<T> {
    abstract Response process(final String url, final EventType eventType);

    abstract Optional<AbstractNotificationHandler<T>> getNotificationHandler();
}
