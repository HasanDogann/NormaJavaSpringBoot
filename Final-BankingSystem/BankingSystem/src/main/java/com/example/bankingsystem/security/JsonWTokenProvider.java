package com.example.bankingsystem.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.net.Authenticator;
import java.util.Date;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */

@Component
@Slf4j
public class JsonWTokenProvider {

    @Value("${application.secret.key}")
    private String SECRET_KEY;
    @Value("${application.expires.time}")
    private long EXPIRES_TIME;

    public String generateJWToken(Authentication authentication) {
        JsonWTUserDetails userDetails = (JsonWTUserDetails) authentication.getPrincipal();
        Date expiredDate = new Date(new Date().getTime() + EXPIRES_TIME);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    Long getUserIdFromJWToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }



    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException
                | MalformedJwtException
                | UnsupportedJwtException
                | ExpiredJwtException
                | IllegalArgumentException e) {
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        Date expiration =
                Jwts.parser().setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token).getBody().getExpiration();

        return expiration.before(new Date());

    }

}
