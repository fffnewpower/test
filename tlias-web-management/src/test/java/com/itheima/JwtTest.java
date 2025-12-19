package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

public class JwtTest {

    //生成令牌
    @Test
    public void testGenerateToken() {
        Map<String, Object> dataMap = Map.of("id",1,"username","admin");
        String itheima1 = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).compact();
        System.out.println(itheima1);
    }

    //解析令牌
    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NDIyNjIxMH0.6MEH8Lu23nn5C--5rxK0pioIuyW2cOPOA8kDkK4JUrs";
        String token2="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiaWQiOjEsImV4cCI6MTc2NDIyNjU5NH0.0oH8M4HxV2rUYt4uokUP1kfOqB94jQcNeMqoHB6fa_Y";
        Claims itheima = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws(token)
                .getBody();
        Claims body = Jwts.parser().setSigningKey("aXRoZWltYQ==").parseClaimsJws(token2).getBody();
        System.out.println(itheima);
        System.out.println(body);


    }
}
