package io.github.hugoltsp.user.infra.config;

import com.hugoltsp.spring.boot.starter.jwt.filter.token.JwtValidator;
import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetailsFactory;
import io.github.hugoltsp.user.data.orm.UserToken;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.data.repository.UserTokenRepository;
import io.github.hugoltsp.user.infra.security.domain.AuthenticatedUserDetails;
import io.github.hugoltsp.user.usecase.domain.UserApiException;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSecurityConfig {

    private final Logger logger;

    public JwtSecurityConfig(Logger logger) {
        this.logger = logger;
    }

    @Bean
    public UserDetailsFactory userDetailsFactory(UserRepository repository) {
        return claims -> repository.findById(Long.valueOf(claims.getSubject())).map(AuthenticatedUserDetails::create);
    }

    @Bean
    public JwtValidator jwtValidator(UserTokenRepository userTokenRepository) {
        return jwt -> {
            if (userTokenRepository.findById(jwt).filter(UserToken::isInactive).isPresent()) {
                logger.info("Token [{}] is no longer valid", jwt);
                throw new UserApiException("Invalid Token");
            }
        };
    }

}
