package com.card.game.security.support.email;

import com.card.game.common.redis.RedisCache;
import com.card.game.common.redis.constants.RedisPrefixConstant;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.exception.BizException;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.card.game.security.support.userdetails.SecurityMailUserDetailsServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.Objects;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:04 PM
 */
@Setter
@Slf4j
public class MailAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private RedisCache redisCache;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailAuthenticationToken mailAuthenticationToken = (MailAuthenticationToken) authentication;
        //邮箱帐号
        String principal = (String) mailAuthenticationToken.getPrincipal();
        //先从redis中取
        SecurityMailUserDetails userDetails = redisCache.getCacheObject(RedisPrefixConstant.AUTHENTICATION_PREFIX + principal);
        //没有获取到查询数据库
        if (Objects.isNull(userDetails)) {
            //查询用户信息
            userDetails = (SecurityMailUserDetails)
                    ((SecurityMailUserDetailsServiceImpl) userDetailsService).loadUserByMailAccount(principal);
        }
        //前端传入的密码
        String password = mailAuthenticationToken.getPassword();
        //进行密码比对
        boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
        //密码比对不通过
        if (!matches) {
            throw new BadCredentialsException(ResultCode.PASSWORD_CHECK_ERROR.getMessage());
        }

        MailAuthenticationToken authenticated = MailAuthenticationToken
                .authenticated(userDetails, Collections.emptyList());
        authenticated.setDetails(userDetails);
        return authenticated;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return MailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
