package io.github.hugoltsp.user.infra.security.domain;

import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetails;
import io.github.hugoltsp.user.data.orm.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

public class AuthenticatedUserDetails implements UserDetails {

    private final String email;
    private final String token;
    private final Claims claims;

    public AuthenticatedUserDetails(String email, String token, Claims claims) {
        this.email = email;
        this.token = token;
        this.claims = claims;
    }

    public AuthenticatedUserDetails(String email, String token) {
        this.email = email;
        this.token = token;
        this.claims = new DefaultClaims().setSubject(email);
    }

    public static AuthenticatedUserDetails create(UserToken userToken) {
        return new AuthenticatedUserDetails(userToken.getUser().getEmail(), userToken.getJwt());
    }

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

}
