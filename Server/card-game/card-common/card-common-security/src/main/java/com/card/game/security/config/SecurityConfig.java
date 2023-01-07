package com.card.game.security.config;

import com.card.game.security.handler.SecurityAccessDeniedHandler;
import com.card.game.security.handler.SecurityAuthenticationEntryPoint;
import com.card.game.security.support.email.MailAuthenticationConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-12:07 PM
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableConfigurationProperties({SecurityUrlProperties.class})
@RequiredArgsConstructor
public class SecurityConfig {

    private final MailAuthenticationConfigurer mailAuthenticationConfigurer;

    private final SecurityUrlProperties securityUrlProperties;


    private final SecurityAccessDeniedHandler securityAccessDeniedHandler;

    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    @Bean
    public DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //关闭csrf
        httpSecurity.csrf().disable();

        //关闭表单登录
        httpSecurity.formLogin().disable();

        //禁用掉session
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //设置未认证处理器
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                .accessDeniedHandler(securityAccessDeniedHandler);


        //拦截请求设置
        httpSecurity.authorizeRequests()
                .antMatchers(securityUrlProperties.getUrls().toArray(new String[0])).permitAll()
                .anyRequest().authenticated();

        //适配邮箱登陆
        httpSecurity.apply(mailAuthenticationConfigurer);
        return httpSecurity.build();
    }



}
