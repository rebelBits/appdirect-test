package com.sample.api.appdirect.user;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.api.appdirect.Response;
import com.sample.api.appdirect.handler.AbstractNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.User;

@RestController
@RequestMapping("api/notification/user/assignment")
public class AssignUserNotificationEndpoint extends AbstractUserEndpoint {

    @Resource(name = "assignUserNotificationHandler")
    private AbstractNotificationHandler<User> notificationHandler;

    @RequestMapping(method = RequestMethod.GET)
    public Response create(@RequestParam("url") final String url) {
        return process(url, EventType.USER_ASSIGNMENT);
    }

    @Override
    public Optional<AbstractNotificationHandler<User>> getNotificationHandler() {
        return Optional.ofNullable(notificationHandler);
    }

    public void setNotificationHandler(final AbstractNotificationHandler<User> notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

}
