package com.sample.api.appdirect.handler.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.Account;
import com.sample.data.appdirect.Payload;
import com.sample.data.appdirect.PayloadUser;
import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.User;
import com.sample.data.dao.UserDao;

public abstract class AbstractUserNotificationHandler extends AbstractNotificationHandler<User> {

    @Autowired
    private UserDao dao;

    protected User buildUserFromEvent(final SubscriptionEvent notification) {
        final Payload payload = notification.getPayload();
        final PayloadUser payloadUser = payload.getUser();
        final Account account = payload.getAccount();
        final long subscriptionId = convertSubscriptionId(account.getAccountIdentifier());

        return User.Builder.forSubscriptionId(subscriptionId)
                .withFirstName(payloadUser.getFirstName())
                .withLastName(payloadUser.getLastName())
                .withOpenId(payloadUser.getOpenId())
                .withUid(payloadUser.getUuid())
                .build();
    }

    public UserDao getDao() {
        return dao;
    }

    public void setDao(final UserDao dao) {
        this.dao = dao;
    }
}
