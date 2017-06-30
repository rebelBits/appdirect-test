package com.sample.api.appdirect.handler.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.User;
import com.sample.data.dao.UserDao;
import com.sample.data.dao.exception.ConflictException;

@Component(value = "assignUserNotificationHandler")
public class AssignUserNotificationHandler extends AbstractUserNotificationHandler {

    @Override
    protected User doProcess(final SubscriptionEvent notification) throws ConflictException {
        final User user = buildUserFromEvent(notification);

        final UserDao userDao = getDao();
        final String uid = user.getUid();
        final long subscriptionId = user.getSubscriptionId();
        final Optional<User> existentUser = userDao.findUser(uid, subscriptionId);
        if (existentUser.isPresent()) {
            throw new ConflictException("User already subscribed");
        }

        userDao.create(user);
        return user;
    }
}
