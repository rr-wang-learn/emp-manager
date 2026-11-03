package com.wangrr.managerment;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ManagermentApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void createJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "wangrr")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    void decodeJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("wangrr")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOiIxIiwiZXhwIjoxNzkzMTA1Njk4fQ._rfCuMFB5k_Caxn2qIpLnEt3SqcGX3EmjoAqnJMN7AM")
                .getBody();
        System.out.println(claims);
    }

}
