package com.card.game.security.support.email;

import com.card.game.common.redis.RedisCache;
import com.card.game.security.handler.SecurityAuthenticationFailureHandler;
import com.card.game.security.handler.mail.SecurityMailAuthenticationSuccessHandler;
import com.card.game.security.support.userdetails.SecurityMailUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:00 PM
 */
@Component
@RequiredArgsConstructor
public class MailAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;

    private final SecurityMailAuthenticationSuccessHandler securityMailAuthenticationSuccessHandler;

    private final SecurityMailUserDetailsServiceImpl securityMailUserDetailsService;

    private final RedisCache redisCache;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        MailAuthenticationFilter mailAuthenticationFilter = new MailAuthenticationFilter();
        //设置AuthenticationManager
        mailAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        //设置认证成功处理器
        mailAuthenticationFilter.setAuthenticationSuccessHandler(securityMailAuthenticationSuccessHandler);
        //设置认证失败处理器
        mailAuthenticationFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);

        //邮箱认证处理器
        MailAuthenticationProvider mailAuthenticationProvider = new MailAuthenticationProvider();
        mailAuthenticationProvider.setUserDetailsService(securityMailUserDetailsService);
        mailAuthenticationProvider.setRedisCache(redisCache);
        mailAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        //设置认证处理器
        builder.authenticationProvider(mailAuthenticationProvider)
                .addFilterAfter(mailAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
