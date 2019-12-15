package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.infra.security.JwtService;
import io.github.hugoltsp.user.usecase.domain.UserApiException;
import io.github.hugoltsp.user.usecase.domain.UserCreationPort;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateUser {

    private final Logger logger;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserTokenService userTokenService;
    private final AuthenticationService authenticationService;

    public CreateUser(Logger logger, JwtService jwtService,
                      UserRepository userRepository,
                      UserTokenService userTokenService,
                      AuthenticationService authenticationService) {
        this.logger = logger;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userTokenService = userTokenService;
        this.authenticationService = authenticationService;
    }

    public User create(UserCreationPort userCreationPort) {
        logger.info("Creating user withe the following e-mail [{}]", userCreationPort.getEmail());
        userRepository.findByEmail(userCreationPort.getEmail())
                .ifPresent(existingUser -> {
                    throw new UserApiException("There's already a user registered with the given e-mail");
                });

        var user = userRepository.save(userCreationPort.asEntity());
        var token = userTokenService.createToken(user);
        user.addToken(token);
        authenticationService.authenticate(token);
        return user;
    }

}
