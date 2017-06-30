package com.sample.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppDirectClient {

    @Autowired
    private ProtectedResourceDetails protectedResourceDetails;

    private OAuthRestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new OAuthRestTemplate(protectedResourceDetails);
    }

    public <T> T getEvent(final String url, final Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public OAuthRestTemplate getRestTemplate() {
        return restTemplate;
    }
}
