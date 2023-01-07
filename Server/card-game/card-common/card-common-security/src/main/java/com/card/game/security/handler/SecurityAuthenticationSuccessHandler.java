package com.card.game.security.handler;


import com.card.game.common.redis.RedisCache;
import com.card.game.common.result.Result;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 *
 * @author tomyou
 * @version 1.0 created on 2022/10/13 14:41
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedisCache redisCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ServletUtils.writeToJson(response, Result.success(ResultCode.AUTHENTICATION_SUCCESS, null));
    }
}
