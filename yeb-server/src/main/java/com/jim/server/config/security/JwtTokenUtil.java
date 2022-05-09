package com.jim.server.config.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED  = "created";

    // 秘钥
    @Value("${jwt.secret}")
    private String secret;
    // 失效时间
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息获取 Token
     * @param userdetails
     * @return
     */
    public String generateToken(UserDetails userdetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userdetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,userdetails.getUsername());
        return generateToken(claims);
    }


    /**
     * 从 Token 中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }



    /**
     * 判断是否可以被刷新（如果过期了就可以进行刷新，否则不行）
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 从
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 刷新 Token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);

    }

    /**
     * 从 Token 中获取荷载
     */
    private Claims getClaimsFormToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }



    /**
     * 判断 Token 是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());

    }





    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据荷载生成 Token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }


}
