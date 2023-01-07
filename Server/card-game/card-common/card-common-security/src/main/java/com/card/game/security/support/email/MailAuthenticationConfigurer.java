package com.card.game.security.support.email;

import com.card.game.security.handler.SecurityAuthenticationFailureHandler;
import com.card.game.security.handler.SecurityAuthenticationSuccessHandler;
import com.card.game.security.support.userdetails.SecurityMailUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    private final SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;

    private final SecurityMailUserDetailsServiceImpl securityMailUserDetailsService;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        MailAuthenticationFilter mailAuthenticationFilter = new MailAuthenticationFilter();
        //设置AuthenticationManager
        mailAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        //设置认证成功处理器
        mailAuthenticationFilter.setAuthenticationSuccessHandler(securityAuthenticationSuccessHandler);
        //设置认证失败处理器
        mailAuthenticationFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);

        //邮箱认证处理器
        MailAuthenticationProvider mailAuthenticationProvider = new MailAuthenticationProvider();
        mailAuthenticationProvider.setUserDetailsService(securityMailUserDetailsService);


        //设置认证处理器
        builder.authenticationProvider(mailAuthenticationProvider)
                .addFilterAfter(mailAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
