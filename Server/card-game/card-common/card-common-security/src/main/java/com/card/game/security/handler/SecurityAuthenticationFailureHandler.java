package com.card.game.security.handler;


import com.card.game.common.result.Result;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author tomyou
 * @version 1.0 created on 2022/10/13 14:42
 */
@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ServletUtils.writeToJson(response, Result.error(ResultCode.AUTHENTICATION_ERROR.getCode(), exception.getMessage()));
    }
}
