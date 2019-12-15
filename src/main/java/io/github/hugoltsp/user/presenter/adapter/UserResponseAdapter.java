package io.github.hugoltsp.user.presenter.adapter;

import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContext;
import com.hugoltsp.spring.boot.starter.jwt.filter.AuthenticationContextHolder;
import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.infra.security.domain.AuthenticatedUserDetails;
import io.github.hugoltsp.user.presenter.domain.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseAdapter {

    private final HttpServletRequest request;

    public UserResponseAdapter(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<?> adapt(User user) {
        return ResponseEntity.ok(toUserResponse(user));
    }

    public ResponseEntity<?> adapt(List<User> users) {
        return users.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(users.stream()
                        .map(this::toUserResponse)
                        .collect(Collectors.toList()));
    }

    private UserResponse toUserResponse(User user) {

        var uri = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/user/{id}")
                .buildAndExpand(user.getId())
                .toUriString();

        var userDetails = AuthenticationContextHolder.getCurrent()
                .map(AuthenticationContext::getUserDetails)
                .get()
                .map(AuthenticatedUserDetails.class::cast).get();

        return new UserResponse(
                user.getId(),
                user.getCreationDate(),
                user.getLastModified(),
                user.getLastLogin(),
                userDetails.getToken(),
                uri
        );

    }


}
