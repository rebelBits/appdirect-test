package com.sample.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class OpenIdLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        final OpenIDAuthenticationToken openIdAuth = (OpenIDAuthenticationToken) authentication;
        try {
            response.sendRedirect(new URIBuilder(new URI("https://www.appdirect.com/applogout")).setParameter("openid", openIdAuth.getIdentityUrl()).toString());
        } catch (final URISyntaxException e) {
            throw new IllegalStateException("Failed to build the redirect URL", e);
        }

        // final Object principal = authentication.getPrincipal();
        // if ((principal == null) ||
        // !principal.getClass().isAssignableFrom(User.class)) {
        // throw new IllegalStateException("Invalid principal");
        // }
        //
        // final String openIdUrl = ((User) principal).getUsername();
        // try {
        // final URI uri = new URI(openIdUrl);
        // response.sendRedirect(new
        // URIBuilder().setScheme(uri.getScheme()).setHost(uri.getHost()).setPort(uri.getPort()).setPath("applogout").setParameter("openid",
        // openIdUrl).build().toString());
        // } catch (final URISyntaxException e) {
        // response.sendRedirect(String.format("https://www.appdirect.com/applogout?openid=%s",
        // openIdUrl));
        // }
    }

}
