package com.card.game.security.repository;

import com.card.game.common.redis.RedisCache;
import com.card.game.common.result.ResultCode;
import com.card.game.security.constant.SecurityConstants;
import com.card.game.security.strategy.SecurityContextManager;
import com.card.game.security.threadlocal.ExceptionThreadLocal;
import com.card.game.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-6:14 PM
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityContextRepositoryImpl extends SecurityContextRepositoryAdapter {

    private final RedisCache redisCache;

    private final ExceptionThreadLocal exceptionThreadLocal;

    private final SecurityContextManager securityContextManager;

    @Override
    public Supplier<SecurityContext> loadContext(HttpServletRequest request) {
        return () -> getSecurityContext(request);
    }

    @SneakyThrows
    public SecurityContext getSecurityContext(HttpServletRequest request) {
        log.info("processing authentication for [{}]", request.getRequestURI());
        //获取token
        String token = request.getHeader(SecurityConstants.AUTHORIZATION);
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();

        if (Objects.isNull(token)) {
            return emptyContext;
        }

        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (SignatureException e) {
            log.error(e.getMessage());
            exceptionThreadLocal.setResultCode(ResultCode.ILLEGAL_TOKEN);
            return emptyContext;
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            exceptionThreadLocal.setResultCode(ResultCode.EXPIRED_TOKEN);
            return emptyContext;
        } catch (Exception e) {
            exceptionThreadLocal.setResultCode(ResultCode.ERROR);
            log.error(e.getMessage());
            return emptyContext;
        }

        return securityContextManager.handleContext(emptyContext,claims);
    }
}
