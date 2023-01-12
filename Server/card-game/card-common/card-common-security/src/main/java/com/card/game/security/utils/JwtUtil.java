package com.card.game.security.utils;


import com.card.game.security.enums.SecurityLoginType;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author tomyou
 * JWT工具类
 */
@Setter
@Component
public class JwtUtil {

    /**
     * 60 * 60 *1000  一个小时
     */

    private static Long JWT_TTL;

    @Value("${jwt.ttl:3600000}")
    private Long SAVE_JWT_TTL;
    /**
     * 设置秘钥明文
     */

    private static String JWT_KEY;

    @Value("${jwt.key:jwt@@}")
    private String SAVE_JWT_KEY;

    /**
     * 设置ISS_USER
     */

    private static String JWT_ISS_USER;

    @Value("${jwt.issUser:tomYou}")
    private String SAVE_JWT_ISS_USER;

    @PostConstruct
    public void init() {
        JWT_TTL = this.SAVE_JWT_TTL;
        JWT_KEY = this.SAVE_JWT_KEY;
        JWT_ISS_USER = this.SAVE_JWT_ISS_USER;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return jwt
     */
    public static String createJWT(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param userDetails 用户信息
     * @return jwt
     */
    public static String createJwt(SecurityMailUserDetails userDetails, SecurityLoginType type) {
        JwtBuilder builder = getJwtBuilder(userDetails.getMailAccount(), null, getUUID(), type.getValue());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return jtw
     */
    public static String createJWT(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        return getJwtBuilder(subject, ttlMillis, uuid, null);
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid, String typeCode) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        Map<String, Object> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(typeCode)) {
            map.put("typeCode", typeCode);
        }
        map.put("subject", subject);
        return Jwts.builder().setId(uuid).setSubject(subject).setClaims(map).setIssuer(JWT_ISS_USER).setIssuedAt(now).signWith(signatureAlgorithm, secretKey).setExpiration(expDate);
    }


    /**
     * 创建token
     *
     * @param id        主键id
     * @param subject   主体
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }


    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return 生成
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getMimeDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt jwt
     * @return Claims
     * @throws Exception 异常
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

}