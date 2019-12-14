package io.github.hugoltsp.user.presenter.domain;

import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContext;
import io.github.hugoltsp.user.presenter.domain.UserRequest.UserPhoneRequest;

import java.util.Set;

public class AuthenticatedUserCreation {

    private final AuthenticationContext authenticationContext;
    private final String name;
    private final String email;
    private final String encodedPassword;
    private final Set<UserPhoneRequest> phones;

    public AuthenticatedUserCreation(AuthenticationContext authenticationContext, String name, String email, String encodedPassword, Set<UserPhoneRequest> phones) {
        this.authenticationContext = authenticationContext;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.phones = phones;
    }

    public AuthenticationContext getAuthenticationContext() {
        return authenticationContext;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public Set<UserPhoneRequest> getPhones() {
        return phones;
    }
}
