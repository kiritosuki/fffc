package com.dddn.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "5oiR5piv5LiA5Liq5a+G6ZKl";     //我是一个密钥 -> base64
    private static Long expire = 43200000L;     //令牌有效期 12h

    //生成jwt令牌 即生成字符串
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder() //开始创建jwt令牌
                .addClaims(claims)  //加入内容
                .signWith(SignatureAlgorithm.HS256, signKey)    //指定签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))   //设置过期时间
                .compact(); //生成令牌
        return jwt;
    }

    //解析jwt令牌 即获取内容 claims是map的子类
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)    //指定密钥
                .parseClaimsJws(jwt)   //解析jwt jwt的header里在生成时就会包含签名算法 不需要显式指定
                .getBody();
        return claims;
    }
}
