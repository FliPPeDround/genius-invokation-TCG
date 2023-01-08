package com.card.game.security.constant;

/**
 * Security常量
 *
 * @author tomyou
 * @version v1.0 2022-10-15-2:37 PM
 */
public interface SecurityConstants {

    String EMAIL_LOGIN_URL = "/mail/login";

    String AUTHORIZATION = "Authorization";

    String USER_INFO = "userInfo";

    String ACCOUNT_DISABLE = "该账户已被禁用";

    String ACCOUNT_LOCKED = "该账号已被锁定";

    String ACCOUNT_EXPIRED = "该账号已过期";

    String USER_NOT_EXIST = "用户不存在";
}
