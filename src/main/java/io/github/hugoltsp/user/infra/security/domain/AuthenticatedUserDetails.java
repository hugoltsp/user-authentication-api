package io.github.hugoltsp.user.infra.security.domain;

import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.time.LocalDateTime;

public class AuthenticatedUserDetails implements UserDetails {

    private final String email;
    private final Claims claims;
    private LocalDateTime passwordUpdateDate;

    public AuthenticatedUserDetails(String email) {
        this.email = email;
        this.claims = new DefaultClaims().setSubject(email);
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getPasswordUpdateDate() {
        return passwordUpdateDate;
    }

    public void setPasswordUpdateDate(LocalDateTime passwordUpdateDate) {
        this.passwordUpdateDate = passwordUpdateDate;
    }

    @Override
    public Claims getClaims() {
        return claims;
    }

}
