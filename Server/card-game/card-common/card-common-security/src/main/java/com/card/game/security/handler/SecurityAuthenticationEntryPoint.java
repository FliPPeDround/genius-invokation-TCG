package com.card.game.security.handler;

import com.card.game.common.result.Result;
import com.card.game.common.web.utils.ServletUtils;
import com.card.game.security.threadlocal.ExceptionThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未认证处理器
 *
 * @author tomyou
 * @version 1.0 created on 2022/10/13 14:44
 */
@Component
@RequiredArgsConstructor
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ExceptionThreadLocal exceptionThreadLocal;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ServletUtils.writeToJson(response, Result.error(exceptionThreadLocal.getResultCode()));
        exceptionThreadLocal.clearContext();
    }
}
