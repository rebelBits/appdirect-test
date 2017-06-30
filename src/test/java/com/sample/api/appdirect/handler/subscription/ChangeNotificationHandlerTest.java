package com.sample.api.appdirect.handler.subscription;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
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
public class ChangeNotificationHandlerTest {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeNotificationHandlerTest.class);
    private static final String URL_SAMPLE = "http://my-host.com";

    @Mock
    private AppDirectClient client;

    @Mock
    private UserDao userDao;

    @Mock
    private SubscriptionDao subscriptionDao;

    private ChangeNotificationHandler handler;

    @Before
    public void setUp() {
        handler = new ChangeNotificationHandler();
        handler.setDao(subscriptionDao);
        handler.setClient(client);
    }

    @Test
    public void processSuccessfully() {
        final SubscriptionEvent appDirectSubscription = buildAppDirectSubcriptionOrderEvent();
        final String openId = appDirectSubscription.getCreator().getOpenId();
        doReturn(appDirectSubscription).when(client).getEvent(URL_SAMPLE, SubscriptionEvent.class);
        doReturn(Optional.empty()).when(subscriptionDao).findByOpenId(openId);

        final Subscription subscription = handler.process(URL_SAMPLE, EventType.SUBSCRIPTION_CHANGE);

        assertThat(subscription, is(IsNull.notNullValue()));
        final Payload payload = appDirectSubscription.getPayload();
        final Account account = payload.getAccount();
        assertThat(subscription.getAccountId(), is(Long.valueOf(account.getAccountIdentifier())));
        assertThat(subscription.getAccountStatus(), is(account.getStatus()));
        assertThat(subscription.getCreatorOpenId(), is(openId));
        assertThat(subscription.getEditionCode(), is(payload.getOrder().getEditionCode()));

        verify(client).getEvent(URL_SAMPLE, SubscriptionEvent.class);
        verify(subscriptionDao).update(any(Subscription.class));
    }

    private SubscriptionEvent buildAppDirectSubcriptionOrderEvent() {
        try (InputStream xmlInputStream = this.getClass().getClassLoader()
                .getResourceAsStream("appdirect-sample-events/subscription-change.xml");) {
            return JAXB.unmarshal(xmlInputStream, SubscriptionEvent.class);
        } catch (final IOException e) {
            LOG.error("Failed to build a SUBSCRIPTION_CHANGE event", e);
            throw new IllegalStateException(e);
        }
    }
}
