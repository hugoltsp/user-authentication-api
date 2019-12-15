package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.orm.UserToken;
import io.github.hugoltsp.user.data.repository.UserTokenRepository;
import io.github.hugoltsp.user.infra.security.JwtService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static io.github.hugoltsp.user.data.orm.UserToken.Status.ACTIVE;

@Service
@Transactional
class UserTokenService {

    private final JwtService jwtService;
    private final UserTokenRepository userTokenRepository;
    private final Logger logger;

    UserTokenService(JwtService jwtService, UserTokenRepository userTokenRepository, Logger logger) {
        this.jwtService = jwtService;
        this.userTokenRepository = userTokenRepository;
        this.logger = logger;
    }

    UserToken createToken(User user) {
        logger.info("Creating a new Token for User [{}]", user.getId());
        var userToken = new UserToken();
        userToken.setCreationDate(LocalDateTime.now());
        userToken.setStatus(ACTIVE);
        userToken.setUser(user);
        userToken.setJwt(jwtService.createJwt(user.getId()));
        return userTokenRepository.save(userToken);
    }

    void invalidateTokens(User user) {
        userTokenRepository.findByUserId(user.getId())
                .stream()
                .peek(UserToken::deactivate)
                .forEach(userTokenRepository::save);
    }

}
