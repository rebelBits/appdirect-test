package com.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.UserDao;

@Controller
public class SubscriptionsController {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/subscription")
    public String subscriptions(final Model model) {
        model.addAttribute("subscriptionList", subscriptionDao.findAll());
        return "subscriptions";
    }

    @RequestMapping("/subscription/{id}/users")
    public String subscriptions(@PathVariable("id") final int subscriptionId, final Model model) {
        model.addAttribute("subscriptionId", subscriptionId);
        model.addAttribute("userList", userDao.findAllUsersForSubscription(subscriptionId));
        return "subscription-users";
    }
}
