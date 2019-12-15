package io.github.hugoltsp.user.presenter.adapter;

import io.github.hugoltsp.user.presenter.domain.UserRequest;
import io.github.hugoltsp.user.usecase.domain.UserCreationPort;
import io.github.hugoltsp.user.usecase.domain.UserCreationPort.PhoneNumberPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PreAuthenticatedUserCreationAdapter {

    private final PasswordEncoder passwordEncoder;

    public PreAuthenticatedUserCreationAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserCreationPort adapt(UserRequest userRequest) {

        return new UserCreationPort(
                userRequest.getName(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getPhones()
                        .stream()
                        .map(p -> new PhoneNumberPort(p.getDdd(), p.getNumber()))
                        .collect(Collectors.toList()));
    }

}
