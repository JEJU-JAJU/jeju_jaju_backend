package com.jejujaju.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private Key key;

    @Value("${jwt.access-token.expiretime}")
    private int accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private int refreshTokenExpireTime;

    @Value("${jwt.key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    private String createToken(Long userId, String loginId, String subject, long expireTime) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("loginId", loginId);
        claims.put("userId", userId);

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createAccessToken(Long userId, String loginId) {
        return createToken(userId, loginId, "access-token", accessTokenExpireTime);
    }

    public String createRefreshToken(Long userId, String loginId) {
        return createToken(userId, loginId, "refresh-token", refreshTokenExpireTime);
    }

    public String getUserLoginId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return (String) claims.get("loginId");
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Long.parseLong(String.valueOf(claims.get("userId")));
    }

    public Claims validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return claims.getBody();
    }
}
