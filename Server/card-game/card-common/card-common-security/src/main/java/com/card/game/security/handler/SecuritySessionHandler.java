package com.card.game.security.handler;


import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;


/**
 * @author tomyou
 * @version 1.0 created on 2022/10/20 11:10
 */
@Component
public class SecuritySessionHandler implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) {

    }
}
