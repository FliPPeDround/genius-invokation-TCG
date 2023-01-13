package com.card.game.security.handler.mail;


import com.card.game.common.redis.RedisCache;
import com.card.game.common.redis.constants.RedisPrefixConstant;
import com.card.game.common.result.Result;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.utils.ServletUtils;
import com.card.game.security.constant.SecurityConstants;
import com.card.game.security.enums.SecurityLoginType;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.card.game.security.utils.JwtUtil;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 认证成功处理器
 *
 * @author tomyou
 * @version 1.0 created on 2022/10/13 14:41
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityMailAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedisCache redisCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityMailUserDetails principal = (SecurityMailUserDetails) authentication.getPrincipal();
        log.info("用户：{} 登录成功", principal.getUsername());
        //生成token
        String token = JwtUtil.createJwt(principal, SecurityLoginType.MAIL);

        //存入redis,1天后过期
        redisCache.setCacheObject(RedisPrefixConstant.AUTHENTICATION_PREFIX + principal.getMailAccount(),
                principal, 24, TimeUnit.HOURS);

        Map<String, Object> result = Maps.newHashMap();
        result.put(SecurityConstants.AUTHORIZATION, token);
        //密码擦除
        principal.clearPassword();
        result.put(SecurityConstants.USER_INFO, principal.getSysUserDTO());
        ServletUtils.writeToJson(response, Result.success(ResultCode.AUTHENTICATION_SUCCESS, result));
    }
}
