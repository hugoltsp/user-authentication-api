package io.github.hugoltsp.user.infra.security;

import com.hugoltsp.spring.boot.starter.jwt.filter.JwtAuthenticationSettings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtService {

    private JwtAuthenticationSettings settings;

    public String createToken(String email) {

        return Jwts.builder()
                .setClaims(new DefaultClaims().setSubject(email))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, settings.getSecretKey())
                .compact();
    }

}
