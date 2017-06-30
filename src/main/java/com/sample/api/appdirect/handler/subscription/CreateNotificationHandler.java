package com.sample.api.appdirect.handler.subscription;

import org.springframework.stereotype.Component;

import com.sample.data.appdirect.Creator;
import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.User;
import com.sample.data.dao.UserDao;
import com.sample.data.dao.exception.ConflictException;

@Component(value = "createSubscriptionNotificationHandler")
public class CreateNotificationHandler extends AbstractSubscriptionNotificationHandler {

    @Override
    protected Subscription doProcess(final SubscriptionEvent notification) throws ConflictException {
        final Subscription subscription = buildSubscriptionFromEvent(notification);

        final SubscriptionDao subscriptionDao = getDao();
        if (subscriptionDao.findByOpenId(subscription.getCreatorOpenId()).isPresent()) {
            throw new ConflictException("Subscription already exists");
        }

        subscription.setAccountId(subscriptionDao.create(subscription));
        createUser(notification.getCreator(), subscription);
        return subscription;
    }

    private void createUser(final Creator creator, final Subscription subscription) {
        final User user = User.Builder.forSubscriptionId(subscription.getAccountId())
                .withFirstName(creator.getFirstName())
                .withLastName(creator.getLastName())
                .withOpenId(creator.getOpenId())
                .withUid(creator.getUuid())
                .build();

        final UserDao userDao = getUserDao();
        if (!userDao.findUser(user.getUid(), user.getSubscriptionId()).isPresent()) {
            userDao.create(user);
        }
    }
}
