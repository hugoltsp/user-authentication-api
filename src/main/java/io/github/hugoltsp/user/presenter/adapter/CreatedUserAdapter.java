package io.github.hugoltsp.user.presenter.adapter;

import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContext;
import io.github.hugoltsp.user.infra.security.domain.AuthenticatedUserDetails;
import io.github.hugoltsp.user.presenter.domain.AuthenticatedUserCreation;
import io.github.hugoltsp.user.presenter.domain.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreatedUserAdapter {

    private final PasswordEncoder passwordEncoder;

    public CreatedUserAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticatedUserCreation adapt(UserRequest userRequest) {
        passwordEncoder.encode(userRequest.getPassword());
        var authenticationContext = new AuthenticationContext(
                Optional.of(new AuthenticatedUserDetails(userRequest.getEmail()))
        );
        return new AuthenticatedUserCreation();
    }

}
