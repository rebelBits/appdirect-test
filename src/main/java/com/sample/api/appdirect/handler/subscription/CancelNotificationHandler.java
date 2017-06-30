package com.sample.api.appdirect.handler.subscription;

import org.springframework.stereotype.Component;

import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.exception.ConflictException;

@Component(value = "cancelSubscriptionNotificationHandler")
public class CancelNotificationHandler extends AbstractSubscriptionNotificationHandler {

    @Override
    protected Subscription doProcess(final SubscriptionEvent notification) throws ConflictException {
        final Subscription subscription = buildSubscriptionFromEvent(notification);
        final SubscriptionDao dao = getDao();
        dao.delete(subscription.getAccountId());
        return subscription;
    }
}