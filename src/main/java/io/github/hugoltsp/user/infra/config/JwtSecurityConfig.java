package io.github.hugoltsp.user.infra.config;

import com.hugoltsp.spring.boot.starter.jwt.filter.userdetails.UserDetailsFactory;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.infra.security.domain.AuthenticatedUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSecurityConfig {

    @Bean
    public UserDetailsFactory userDetailsFactory(UserRepository repository) {
        return claims -> repository.findById(Long.valueOf(claims.getSubject())).map(AuthenticatedUserDetails::create);
    }

}
