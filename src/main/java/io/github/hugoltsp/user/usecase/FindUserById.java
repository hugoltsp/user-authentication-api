package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.usecase.domain.UserApiAuthenticationException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class FindUserById {

    private final Logger logger;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public FindUserById(Logger logger, UserRepository userRepository, AuthenticationService authenticationService) {
        this.logger = logger;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public Optional<User> find(Long id) {
        logger.info("fetching user by id [{}]", id);

        if (authenticationService.getCurrentAuthenticatedUserId()
                .filter(currentId -> !currentId.equals(id))
                .isPresent()) {
            throw new UserApiAuthenticationException("The given id doesn't match the current authenticated user");
        }

        return userRepository.findById(id);
    }

}
