package com.sample.data.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SubscriptionDaoTest {
    private static final String EDITION_CODE_SAMPLE = "some-edition-code";
    private static final String MARKETPLACE_URL_SAMPLE = "some-market-place-url";

    @Autowired
    public SubscriptionDao subscriptionDao;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void subscriptionCreate() {
        final String openId = UUID.randomUUID().toString();
        final long id = subscriptionDao.create(buildSampleSubscription(openId));
        assertThat(id, greaterThan(0l));

        final Optional<Subscription> subscription = subscriptionDao.findByOpenId(openId);
        assertTrue(subscription.isPresent());
        final Subscription createdSubscription = subscription.get();
        assertThat(id, is(Long.valueOf(createdSubscription.getAccountId())));
        assertThat(openId, is(createdSubscription.getCreatorOpenId()));
    }

    private Subscription buildSampleSubscription(final String openId) {
        final Subscription subscription = Subscription.Builder.forCreatorOpenId(openId)
                .withMarketplaceUrl(MARKETPLACE_URL_SAMPLE).withEditionCode(EDITION_CODE_SAMPLE).build();
        return subscription;
    }

    @Test
    public void subscriptionList() {
        subscriptionDao.create(buildSampleSubscription(UUID.randomUUID().toString()));
        final List<Subscription> subscriptions = subscriptionDao.findAll();
        assertThat(subscriptions.size(), greaterThan(0));
    }

    @Test
    public void subscriptionFind() {
        final String openId = UUID.randomUUID().toString();
        subscriptionDao.create(buildSampleSubscription(openId));
        final Optional<Subscription> subscription = subscriptionDao.findByOpenId(openId);
        assertTrue(subscription.isPresent());
        final Subscription createdSubscription = subscription.get();
        assertEquals(EDITION_CODE_SAMPLE, createdSubscription.getEditionCode());
        assertEquals(MARKETPLACE_URL_SAMPLE, createdSubscription.getMarketplaceUrl());
        assertThat(openId, is(createdSubscription.getCreatorOpenId()));
    }

    @After
    public void cleanup() {
        subscriptionDao.findAll().stream().forEach(e -> subscriptionDao.delete(e.getAccountId()));
    }
}
