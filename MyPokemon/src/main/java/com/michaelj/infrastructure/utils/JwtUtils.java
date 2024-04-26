package com.michaelj.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SIGN_KEY = "c2VjcmV0"; // "secret" Base64编码
    private static final Long EXPIRE = (long)(6 * 3600 * 1000); // 设置有效期为 6h

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .compact();

        return token;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }

    public static Long getExpire() {
        return EXPIRE;
    }
}
