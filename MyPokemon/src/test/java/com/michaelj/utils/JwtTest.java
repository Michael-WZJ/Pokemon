package com.michaelj.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    private Map<String, Object> genClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Tom");
        return claims;
    }

    // 生成JWT
    @Test
    public void testGenJwt() {
        System.out.println(JwtUtils.generateJwt(genClaims()));
        //System.out.println(JwtUtils.getExpire());
    }

    // 解析JWT
    @Test
    public void testParseJwt() {
        String token = JwtUtils.generateJwt(genClaims());
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTY4Nzg3NzkyMn0.P-xhQat9FJ9ZzNwnF6DUXQ3Lva9sHvGEP8Ic55Y4fHw";
        System.out.println(JwtUtils.parseJwt(token));
    }
}
