package io.github.hugoltsp.user.infra.security.domain;

import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContext;
import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContextHolder;
import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetails;
import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.orm.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Optional;

public class AuthenticatedUserDetails implements UserDetails {

    private final Long id;
    private final String email;
    private final Claims claims;
    private final String token;

    public AuthenticatedUserDetails(String email, Long id, String token) {
        this.email = email;
        this.id = id;
        this.claims = new DefaultClaims().setSubject(id.toString());
        this.token = token;
    }

    public static AuthenticatedUserDetails create(UserToken userToken) {
        return new AuthenticatedUserDetails(userToken.getUser().getEmail(), userToken.getUser().getId(), userToken.getJwt());
    }

    public static AuthenticatedUserDetails create(User user) {
        return new AuthenticatedUserDetails(user.getEmail(), user.getId(), null);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Claims getClaims() {
        return claims;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public static Optional<AuthenticatedUserDetails> current() {
        return AuthenticationContextHolder.getCurrent()
                .map(AuthenticationContext::getUserDetails)
                .get()
                .map(AuthenticatedUserDetails.class::cast);
    }
}
