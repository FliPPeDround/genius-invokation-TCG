package com.card.game.security.support.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:00 PM
 */
public abstract class UserDetailsServiceAdapter implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 通过邮箱帐号获取用户信息
     *
     * @param mailAccount mailAccount
     * @return user details
     */
    public abstract UserDetails loadUserByMailAccount(String mailAccount);

}
