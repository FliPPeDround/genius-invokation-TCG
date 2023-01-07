package com.card.game.security.support.email;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:04 PM
 */
@Setter
@Slf4j
public class MailAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
