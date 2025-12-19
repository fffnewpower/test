package com.itheima.utils;

import com.itheima.pojo.LoginInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    //定义密钥
    private static final String SECRET = "itheima";
    //定义token的有效时间
    private static final long EXPIRE = 1000 * 60 * 60 * 12;

    /*
    * 生成密钥
    * */
    public static String createJwt(Map<String,Object> claims){
        return Jwts.builder()
                .addClaims( claims)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .compact();
    }

    /*
    * 解析密钥
    * */
    public static Map<String, Object> parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
