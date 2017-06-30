package com.sample.api.appdirect.handler.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.User;
import com.sample.data.dao.UserDao;
import com.sample.data.dao.exception.NotFoundException;

@Component(value = "unassignUserNotificationHandler")
public class UnassignUserNotificationHandler extends AbstractUserNotificationHandler {

    @Override
    protected User doProcess(final SubscriptionEvent notification) {
        final User user = buildUserFromEvent(notification);

        final UserDao userDao = getDao();
        final String uid = user.getUid();
        final long subscriptionId = user.getSubscriptionId();
        final Optional<User> existentUser = userDao.findUser(uid, subscriptionId);
        if (!existentUser.isPresent()) {
            throw new NotFoundException("User not found");
        }

        userDao.delete(uid, subscriptionId);
        return user;
    }

}
