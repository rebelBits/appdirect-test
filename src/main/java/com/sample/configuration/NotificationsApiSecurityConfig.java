package com.sample.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@Order(1)
public class NotificationsApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${oauth.consumer.key}")
    private String consumerKey;

    @Value("${oauth.consumer.secret}")
    private String consumerSecret;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(oauthProviderProcessingFilter(), LogoutFilter.class);
    }

    public OAuthProviderProcessingFilter oauthProviderProcessingFilter() {
        final ProtectedResourceProcessingFilter filter = new ProtectedResourceProcessingFilter();
        filter.setConsumerDetailsService(createConsumerDetailsService());
        filter.setTokenServices(oauthProviderTokenServices());
        filter.setIgnoreMissingCredentials(false);
        return filter;
    }

    public ConsumerDetailsService createConsumerDetailsService() {
        final InMemoryConsumerDetailsService service = new InMemoryConsumerDetailsService();
        service.setConsumerDetailsStore(Collections.singletonMap(consumerKey,
                createConsumerDetails()));
        return service;
    }

    private ConsumerDetails createConsumerDetails() {
        final BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
        consumerDetails.setConsumerKey(consumerKey);
        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(consumerSecret));
        consumerDetails.setRequiredToObtainAuthenticatedToken(false);
        return consumerDetails;
    }

    @Bean
    public ProtectedResourceDetails protectedResourceDetails() {
        final BaseProtectedResourceDetails resource = new BaseProtectedResourceDetails();
        resource.setConsumerKey(consumerKey);
        resource.setSharedSecret(new SharedConsumerSecretImpl(consumerSecret));
        return resource;
    }

    public OAuthProviderTokenServices oauthProviderTokenServices() {
        return new InMemoryProviderTokenServices();
    }

}
