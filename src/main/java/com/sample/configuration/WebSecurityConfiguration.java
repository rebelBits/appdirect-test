package com.sample.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.sample.web.OpenIdLogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new OpenIdLogoutHandler());

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**")
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/**")
                .hasRole("USER")
                .and()
                .openidLogin()
                .loginProcessingUrl("/login/openId")
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/subscription")
                .authenticationUserDetailsService(
                        new AutoProvisioningUserDetailsService());
    }

    public class AutoProvisioningUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

        @Override
        public UserDetails loadUserDetails(final OpenIDAuthenticationToken token)
                throws UsernameNotFoundException {
            return new User(token.getName(), "NOTUSED", AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
    }
}
