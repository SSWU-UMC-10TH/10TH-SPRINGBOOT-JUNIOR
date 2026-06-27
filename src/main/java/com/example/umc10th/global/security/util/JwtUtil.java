package com.example.umc10th.global.security.util;

import com.example.umc10th.domain.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // Access Token 생성
    public String createAccessToken(User user) {
        return createToken(user, accessExpiration);
    }

    // 토큰에서 이메일 가져오기 (@param token 유저 정보를 추출할 토큰, @return 유저 이메일을 토큰에서 추출
    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    // 토큰 유효성 확인 (@param token 유효한지 확인할 토큰, @return True/False 반환)
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰 생성
    private String createToken(User user, Duration expiration) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getEmail()) // user 이메일을 subject로
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .issuedAt(Date.from(now)) // 언제 발급한 건지
                .expiration(Date.from((now.plus(expiration)))) // 언제까지 유효한지
                .signWith(secretKey) // sign할 key
                .compact();
    }

    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseClaimsJws(token);
    }
}
