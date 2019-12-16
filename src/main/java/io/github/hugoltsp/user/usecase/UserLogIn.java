package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.presenter.domain.LoginRequest;
import io.github.hugoltsp.user.usecase.domain.UserApiAuthenticationException;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserLogIn {

    private final UserRepository userRepository;
    private final UserTokenService userTokenService;
    private final Logger logger;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public UserLogIn(UserRepository userRepository, UserTokenService userTokenService,
                     Logger logger, PasswordEncoder passwordEncoder,
                     AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.userTokenService = userTokenService;
        this.logger = logger;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    public User login(LoginRequest login) {
        logger.info("Logging in user [{}]", login.getEmail());
        var user = userRepository.findByEmail(login.getEmail())
                .filter(u -> passwordEncoder.matches(login.getPassword(), u.getPassword()))
                .orElseThrow(() -> new UserApiAuthenticationException("Unable to authenticate user, invalid password or email"));
        user.setLastLogin(LocalDateTime.now());

        userTokenService.invalidateTokens(user);
        authenticationService.authenticate( userTokenService.createToken(user));
        return user;
    }

}
