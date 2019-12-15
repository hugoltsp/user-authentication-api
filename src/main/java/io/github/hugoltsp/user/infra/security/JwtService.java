package io.github.hugoltsp.user.infra.security;

import com.hugoltsp.spring.boot.starter.jwt.filter.JwtAuthenticationSettings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtService {

    private final JwtAuthenticationSettings settings;
    private final Long expiration;

    public JwtService(JwtAuthenticationSettings settings, @Value("${app.token-expiration-minutes}") Long expiration) {
        this.settings = settings;
        this.expiration = expiration;
    }

    public String createJwt(Long id) {

        return Jwts.builder()
                .setClaims(new DefaultClaims().setSubject(id.toString()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(expiration, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, settings.getSecretKey())
                .compact();
    }

}
