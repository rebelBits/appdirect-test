package com.sample.api.appdirect.user;

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
import com.sample.api.appdirect.handler.user.AbstractUserNotificationHandler;
import com.sample.data.appdirect.EventType;
import com.sample.data.dao.exception.ConflictException;
import com.sample.data.dao.exception.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractUserEndpointTest {

    protected static final String URL_SAMPLE = "http://my-host.com";

    private MockMvc mockMvc;

    protected AbstractUserEndpoint endpoint;

    @Mock
    private AbstractUserNotificationHandler handler;

    @Before
    public void setUp() {
        endpoint = createNotificationHandler();
        mockMvc = standaloneSetup(endpoint).build();
    }

    protected abstract AbstractUserEndpoint createNotificationHandler();

    protected abstract String getEndpointPath();

    protected abstract EventType getEventType();

    @Test
    public void notificationHandleFailsWithConflicException() throws Exception {
        doThrow(ConflictException.class).when(handler).process(URL_SAMPLE, getEventType());

        mockMvc.perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(Boolean.FALSE))
                .andExpect(jsonPath("$.errorCode").value("USER_ALREADY_EXISTS"));
    }

    @Test
    public void notificationHandleFailsWithNotFoundException() throws Exception {
        doThrow(NotFoundException.class).when(handler).process(URL_SAMPLE, getEventType());

        mockMvc.perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(Boolean.FALSE))
                .andExpect(jsonPath("$.errorCode").value("USER_NOT_FOUND"));
    }

    @Test
    public void notificationHandleFailsWithInvalidNotificationException() throws Exception {
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

    public AbstractUserNotificationHandler getHandler() {
        return handler;
    }

    public void setHandler(final AbstractUserNotificationHandler handler) {
        this.handler = handler;
    }
}
