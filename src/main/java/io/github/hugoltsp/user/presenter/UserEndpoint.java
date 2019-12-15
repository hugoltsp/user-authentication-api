package io.github.hugoltsp.user.presenter;

import io.github.hugoltsp.user.presenter.adapter.PreAuthenticatedUserCreationAdapter;
import io.github.hugoltsp.user.presenter.adapter.UserResponseAdapter;
import io.github.hugoltsp.user.presenter.domain.UserRequest;
import io.github.hugoltsp.user.usecase.CreateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserEndpoint {

    private final PreAuthenticatedUserCreationAdapter adapter;
    private final UserResponseAdapter outputAdapter;
    private final CreateUser createUser;

    public UserEndpoint(PreAuthenticatedUserCreationAdapter adapter,
                        CreateUser createUser,
                        UserResponseAdapter outputAdapter) {
        this.adapter = adapter;
        this.createUser = createUser;
        this.outputAdapter = outputAdapter;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest userRequest) {
        return outputAdapter.adapt(createUser.create(adapter.adapt(userRequest)));
    }

}
