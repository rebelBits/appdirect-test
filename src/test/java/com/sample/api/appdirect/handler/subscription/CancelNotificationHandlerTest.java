package com.sample.api.appdirect.handler.subscription;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.xml.bind.JAXB;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.data.appdirect.Account;
import com.sample.data.appdirect.Payload;
import com.sample.data.appdirect.SubscriptionEvent;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.Subscription;
import com.sample.data.dao.SubscriptionDao;
import com.sample.data.dao.UserDao;
import com.sample.utils.AppDirectClient;

@RunWith(MockitoJUnitRunner.class)
public class CancelNotificationHandlerTest {
    private static final Logger LOG = LoggerFactory.getLogger(CancelNotificationHandlerTest.class);
    private static final String URL_SAMPLE = "http://my-host.com";

    @Mock
    private AppDirectClient client;

    @Mock
    private UserDao userDao;

    @Mock
    private SubscriptionDao subscriptionDao;

    private CancelNotificationHandler handler;

    @Before
    public void setUp() {
        handler = new CancelNotificationHandler();
        handler.setDao(subscriptionDao);
        handler.setClient(client);
    }

    @Test
    public void processSuccessfully() {
        final SubscriptionEvent appDirectSubscription = buildAppDirectSubcriptionOrderEvent();
        final String openId = appDirectSubscription.getCreator().getOpenId();
        doReturn(appDirectSubscription).when(client).getEvent(URL_SAMPLE, SubscriptionEvent.class);
        doReturn(Optional.empty()).when(subscriptionDao).findByOpenId(openId);

        final Subscription subscription = handler.process(URL_SAMPLE, EventType.SUBSCRIPTION_CANCEL);

        assertThat(subscription, is(IsNull.notNullValue()));
        final Payload payload = appDirectSubscription.getPayload();
        final Account account = payload.getAccount();
        final long accountId = subscription.getAccountId();
        assertThat(accountId, is(Long.valueOf(account.getAccountIdentifier())));
        assertThat(subscription.getAccountStatus(), is(account.getStatus()));
        assertThat(subscription.getCreatorOpenId(), is(openId));

        verify(client).getEvent(URL_SAMPLE, SubscriptionEvent.class);
        verify(subscriptionDao).delete(accountId);
    }

    private SubscriptionEvent buildAppDirectSubcriptionOrderEvent() {
        try (InputStream xmlInputStream = this.getClass().getClassLoader()
                .getResourceAsStream("appdirect-sample-events/subscription-cancel.xml");) {
            return JAXB.unmarshal(xmlInputStream, SubscriptionEvent.class);
        } catch (final IOException e) {
            LOG.error("Failed to build a SUBSCRIPTION_CANCEL event", e);
            throw new IllegalStateException(e);
        }
    }
}
