package com.sample.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.data.dao.Subscription;
import com.sample.data.dao.SubscriptionDao;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionService {

    @Autowired
    private SubscriptionDao dao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Subscription> list() {
        return dao.findAll();
    }
}
