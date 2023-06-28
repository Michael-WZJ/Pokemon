package com.michaelj;

import com.michaelj.utils.JwtUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 生成JWT
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Tom");

        System.out.println(JwtUtils.generateJwt(claims));
        //System.out.println(JwtUtils.getExpire());
    }

    // 解析JWT
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTY4Nzg3NzkyMn0.P-xhQat9FJ9ZzNwnF6DUXQ3Lva9sHvGEP8Ic55Y4fHw";
        System.out.println(JwtUtils.parseJwt(token));
    }
}
