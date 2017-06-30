package com.sample.api.appdirect.subscription;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.api.appdirect.Response;
import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.Subscription;

@RestController
@RequestMapping("api/notification/subscription/create")
public class CreateSubscriptionEndpoint extends AbstractSubscriptionEndpoint {

    @Resource(name = "createSubscriptionNotificationHandler")
    private AbstractNotificationHandler<Subscription> notificationHandler;

    @RequestMapping(method = RequestMethod.GET)
    public Response create(@RequestParam("url") final String url) {
        return process(url, EventType.SUBSCRIPTION_ORDER);
    }

    @Override
    public Optional<AbstractNotificationHandler<Subscription>> getNotificationHandler() {
        return Optional.ofNullable(notificationHandler);
    }

    @Override
    protected boolean isSubscriptionAccountIdRequired() {
        return Boolean.TRUE;
    }

    public void setNotificationHandler(final AbstractNotificationHandler<Subscription> notificationHandler) {
        this.notificationHandler = notificationHandler;
    }
}
