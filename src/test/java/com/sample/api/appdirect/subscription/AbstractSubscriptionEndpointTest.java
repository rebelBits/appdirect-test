package com.sample.api.appdirect.subscription;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.api.appdirect.exception.InvalidNotificationException;
import com.sample.api.appdirect.handler.subscription.AbstractSubscriptionNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.exception.ConflictException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractSubscriptionEndpointTest {

    protected static final String URL_SAMPLE = "http://my-host.com";

    private MockMvc mockMvc;

    protected AbstractSubscriptionEndpoint endpoint;

    @Mock
    private AbstractSubscriptionNotificationHandler handler;

    @Before
    public void setUp() {
        endpoint = createNotificationHandler();
        mockMvc = standaloneSetup(endpoint).build();
    }

    protected abstract AbstractSubscriptionEndpoint createNotificationHandler();

    protected abstract String getEndpointPath();

    protected abstract EventType getEventType();

    @Test
    public void subscriptionHandleFailsWithConflicException() throws Exception {
        doThrow(ConflictException.class).when(handler).process(URL_SAMPLE, getEventType());

        mockMvc.perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(Boolean.FALSE))
                .andExpect(jsonPath("$.errorCode").value("INVALID_RESPONSE"));
    }

    @Test
    public void subscriptionHandleFailsWithInvalidNotificationException() throws Exception {
        doThrow(InvalidNotificationException.class).when(handler).process(URL_SAMPLE, getEventType());

        mockMvc.perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(Boolean.FALSE))
                .andExpect(jsonPath("$.errorCode").value("INVALID_RESPONSE"));
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(final MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public AbstractSubscriptionNotificationHandler getHandler() {
        return handler;
    }

    public void setHandler(final AbstractSubscriptionNotificationHandler handler) {
        this.handler = handler;
    }
}
