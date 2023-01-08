package com.card.game.security.strategy;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-8:56 PM
 */
public interface SecurityContextProvider {

    /**
     * 处理SecurityContext
     *
     * @param securityContext securityContext
     * @param claims          claims
     * @return SecurityContext
     */
    SecurityContext handleContext(SecurityContext securityContext, Claims claims);

    /**
     * 判断是否支持
     *
     * @param typeCode 类型
     * @return 是否支持
     */
    boolean supports(String typeCode);
}
