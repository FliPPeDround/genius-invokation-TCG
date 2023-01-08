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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:04 PM
 */
@Setter
@Slf4j
public class MailAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private RedisCache redisCache;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailAuthenticationToken mailAuthenticationToken = (MailAuthenticationToken) authentication;
        //邮箱帐号
        String principal = (String) mailAuthenticationToken.getPrincipal();
        //前端传入的code
        String mailCode = mailAuthenticationToken.getMailCode();
        //通过邮箱帐号获取验证码
        String redisKey = RedisPrefixConstant.MAIL_CODE_PREFIX + principal;
        String realCode = redisCache.getCacheObject(redisKey);
        //从redis中没有获取到code说明code已经过期
        if (StringUtils.isBlank(realCode)) {
            throw new BizException(ResultCode.MAIL_CODE_IS_EXPIRE);
        }
        //前端传入的code和redis中的code比对不通过 说明code有误
        if (!(StringUtils.equals(realCode, mailCode))) {
            throw new BizException(ResultCode.MAIL_CODE_CHECK_ERROR);
        }

        //查询用户信息
        SecurityMailUserDetails userDetails = (SecurityMailUserDetails)
                ((SecurityMailUserDetailsServiceImpl) userDetailsService).loadUserByMailAccount(principal);

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
