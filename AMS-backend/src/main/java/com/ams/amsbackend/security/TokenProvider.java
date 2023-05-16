package com.ams.amsbackend.security;

import com.ams.amsbackend.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private static final String MY_KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLUFNUy1CQUNLRU5ELXNwcmluZy1ib290LXNlY3VyaXR5LWp3dC10dXRvcmlhbA0K";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(MY_KEY));

    public String create(final Authentication authentication) {
        OAuth2User userPrincipal = (OAuth2User) authentication.getPrincipal();
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SECRET_KEY,SignatureAlgorithm.HS512)
                .compact();
    }

    public String create(UserEntity userEntity) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS)
        );
        //Token 생성
        return Jwts.builder()
                //header
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                //payload
                .setSubject(userEntity.getLoginId())
                .setIssuer("ams")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    /**
     * 발생가능한 Exception
     * io.jsonwebtoken.MalformedJwtException, 올바르지않은 jwt 형식
     * io.jsonwebtoken.SignatureException, jwt signature 유효하지 않을때
     * io.jsonwebtoken.ExpiredJwtException, 토큰이 만료되었을때
     * io.jsonwebtoken.UnsupportedJwtException, 지원되지않는 기능을 사용할때 ex)암호화 알고리즘이 지원되지않는경우
     * io.jsonwebtoken.security.SecurityException, 보안검사에 실패한경우
     */
    public String validateAndGetLogInId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
