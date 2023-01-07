package com.card.game.security.support.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:21 PM
 */
@Service
@RequiredArgsConstructor
public class SecurityMailUserDetailsServiceImpl extends UserDetailsServiceAdapter {


    @Override
    public UserDetails loadUserByMailAccount(String mailAccount) {
        return null;
    }
}
