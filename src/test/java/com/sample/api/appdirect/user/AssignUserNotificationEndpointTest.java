package com.sample.api.appdirect.user;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.sample.data.appdirect.EventType;
import com.sample.data.dao.User;

public class AssignUserNotificationEndpointTest extends AbstractUserEndpointTest {

    private static final String ENDPOINT_PATH = "/api/notification/user/assignment";

    @Test
    public void assignUserSuccessfully() throws Exception {
        final User subscription = User.Builder.forSubscriptionId(1)
                .withFirstName("first-name")
                .withLastName("last-name")
                .build();

        doReturn(subscription).when(getHandler()).process(URL_SAMPLE, getEventType());

        getMockMvc().perform(get(getEndpointPath())
                .param("url", URL_SAMPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(Boolean.TRUE));
    }

    @Override
    protected AbstractUserEndpoint createNotificationHandler() {
        final AssignUserNotificationEndpoint endpoint = new AssignUserNotificationEndpoint();
        endpoint.setNotificationHandler(getHandler());
        return endpoint;
    }

    @Override
    protected String getEndpointPath() {
        return ENDPOINT_PATH;
    }

    @Override
    protected EventType getEventType() {
        return EventType.USER_ASSIGNMENT;
    }

}
