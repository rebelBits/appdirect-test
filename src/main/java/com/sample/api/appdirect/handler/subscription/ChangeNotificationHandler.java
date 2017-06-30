package com.sample.api.appdirect.handler.subscription;

import org.springframework.stereotype.Component;

import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.exception.ConflictException;

@Component(value = "changeSubscriptionNotificationHandler")
public class ChangeNotificationHandler extends AbstractSubscriptionNotificationHandler {

    @Override
    protected Subscription doProcess(final SubscriptionEvent notification) throws ConflictException {
        final Subscription subscription = buildSubscriptionFromEvent(notification);
        final SubscriptionDao dao = getDao();
        dao.update(subscription);
        return subscription;
    }
}
