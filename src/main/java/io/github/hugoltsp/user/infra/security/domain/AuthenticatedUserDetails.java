package io.github.hugoltsp.user.infra.security.domain;

import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

public class AuthenticatedUserDetails implements UserDetails {

    private String email;
    private String token;
    private Claims claims;

    public String getEmail() {
        return email;
    }

    @Override
    public Claims getClaims() {
        return claims;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
