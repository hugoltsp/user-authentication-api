package io.github.hugoltsp.user.usecase;

import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContext;
import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContextHolder;
import io.github.hugoltsp.user.data.orm.UserToken;
import io.github.hugoltsp.user.infra.security.domain.AuthenticatedUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthenticationService {

    void authenticate(UserToken userToken) {
        AuthenticationContextHolder.set(
                new AuthenticationContext(
                        Optional.of(
                                AuthenticatedUserDetails.create(userToken)
                        )
                )
        );
    }

}
