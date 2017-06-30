package com.sample.api.appdirect.subscription;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.sample.data.appdirect.EventType;
import com.sample.data.dao.Subscription;

public class CancelSubscriptionEndpointTest extends AbstractSubscriptionEndpointTest {

    private static final String ENDPOINT_PATH = "/api/notification/subscription/cancel";

    @Test
    public void cancelSubscriptionSuccessfully() throws Exception {
        final long id = new Random().nextLong();
        final Subscription subscription = Subscription.Builder.forCreatorOpenId(UUID.randomUUID().toString())
                .withAccountId(id)
                .build();

        doReturn(subscription).when(getHandler()).process(URL_SAMPLE, getEventType());

        getMockMvc().perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(Boolean.TRUE));
    }

    @Override
    protected AbstractSubscriptionEndpoint createNotificationHandler() {
        final CancelSubscriptionEndpoint endpoint = new CancelSubscriptionEndpoint();
        endpoint.setNotificationHandler(getHandler());
        return endpoint;
    }

    @Override
    protected String getEndpointPath() {
        return ENDPOINT_PATH;
    }

    @Override
    protected EventType getEventType() {
        return EventType.SUBSCRIPTION_CANCEL;
    }
}
