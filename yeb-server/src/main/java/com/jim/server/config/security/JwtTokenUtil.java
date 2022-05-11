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
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @Author: Jim
     * @Description: 根据用户信息生成 TOKEN
     * @Params:  public
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * @Author: Jim
     * @Description: 从 token 中获取登录用户名
     * @Params: 
     */
    public String getUserNameFromToken(String token){
        String username;
        // 根据 token 拿一个荷载
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
     * @Author: Jim
     * @Description: 判断 token 是否有效
     * @Params:
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername() ) && ! isTokenExpired(token);
    }

    /**
     * @Author: Jim
     * @Description: 判断 token 是否可以刷新
     * @Params:
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * @Author: Jim
     * @Description: 刷新 token
     * @Params:
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * @Author: Jim
     * @Description: 判断 token 是否失效
     * @Params:
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * @Author: Jim
     * @Description: 从 token 中获取过期时间
     * @Params:
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();

    }

    /**
     * @Author: Jim
     * @Description: 从 token 中获取荷载
     * @Params:
     */
    private Claims getClaimsFromToken(String token) {
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
     * @Author: Jim
     * @Description: 根据荷载生成 JWT TOKEN
     * @Params: 
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.RS512,secret)
                .compact();
    }

    /**
     * @Author: Jim
     * @Description: 生成 TOKEN 失效时间
     * @Params:
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
}
