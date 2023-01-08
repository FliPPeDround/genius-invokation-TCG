package com.card.game.security.strategy;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-8:45 PM
 */
public interface SecurityContextManager {
    /**
     * 处理SecurityContext
     *
     * @param securityContext securityContext
     * @param claims          claims
     * @return SecurityContext
     */
    SecurityContext handleContext(SecurityContext securityContext, Claims claims);
}
