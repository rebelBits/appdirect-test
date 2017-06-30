package com.sample.api.appdirect.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.appdirect.exception.InvalidNotificationException;
import com.sample.data.appdirect.EventType;
import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.utils.AppDirectClient;

public abstract class AbstractNotificationHandler<R> {

    @Autowired
    private AppDirectClient client;

    public R process(final String url, final EventType type) throws InvalidNotificationException {
        final SubscriptionEvent notification = client.getEvent(url, SubscriptionEvent.class);
        final EventType notificationType = notification.getType();
        if ((notificationType != null) && !notificationType.equals(type)) {
            throw new InvalidNotificationException(
                    String.format("Invalid notification type. [url='%s', expectedType='%s', notificationType='%s']",
                            url, type, notificationType));
        }

        return doProcess(notification);
    }

    protected long convertSubscriptionId(final String subscriptionId) {
        try {
            return Long.valueOf(subscriptionId);
        } catch (final NumberFormatException e) {
            throw new InvalidNotificationException(e);
        }
    }

    protected abstract R doProcess(SubscriptionEvent notification);

    public AppDirectClient getClient() {
        return client;
    }

    public void setClient(final AppDirectClient client) {
        this.client = client;
    }
}
