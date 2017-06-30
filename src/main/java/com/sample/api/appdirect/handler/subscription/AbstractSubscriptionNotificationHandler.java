package com.sample.api.appdirect.handler.subscription;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.Account;
import com.sample.data.appdirect.Creator;
import com.sample.data.appdirect.Marketplace;
import com.sample.data.appdirect.Order;
import com.sample.data.appdirect.Payload;
import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.Subscription.Builder;
import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.UserDao;

public abstract class AbstractSubscriptionNotificationHandler extends AbstractNotificationHandler<Subscription> {

    @Autowired
    private SubscriptionDao dao;

    @Autowired
    private UserDao userDao;

    protected Subscription buildSubscriptionFromEvent(final SubscriptionEvent notification) {
        final Creator creator = notification.getCreator();
        final Payload payload = notification.getPayload();
        final Account account = payload.getAccount();
        final Order order = payload.getOrder();
        final Marketplace marketplace = notification.getMarketplace();

        Builder builder = Builder.forCreatorOpenId(creator.getOpenId());
        if (account != null) {
            builder = builder.withAccountId(convertSubscriptionId(account.getAccountIdentifier()))
                    .withAccountStatus(account.getStatus());
        }

        if (marketplace != null) {
            builder = builder.withMarketplaceUrl(marketplace.getBaseUrl());
        }

        if (order != null) {
            builder = builder.withEditionCode(order.getEditionCode());
        }

        return builder.build();
    }

    public SubscriptionDao getDao() {
        return dao;
    }

    public void setDao(final SubscriptionDao dao) {
        this.dao = dao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

}
