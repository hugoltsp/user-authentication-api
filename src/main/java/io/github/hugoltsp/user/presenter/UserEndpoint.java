package io.github.hugoltsp.user.presenter;

import io.github.hugoltsp.user.presenter.adapter.PreAuthenticatedUserCreationAdapter;
import io.github.hugoltsp.user.presenter.adapter.UserResponseAdapter;
import io.github.hugoltsp.user.presenter.domain.UserRequest;
import io.github.hugoltsp.user.usecase.CreateUser;
import io.github.hugoltsp.user.usecase.FindUserById;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserEndpoint {

    private final PreAuthenticatedUserCreationAdapter adapter;
    private final UserResponseAdapter outputAdapter;
    private final CreateUser createUser;
    private final FindUserById findUserById;

    public UserEndpoint(PreAuthenticatedUserCreationAdapter adapter,
                        CreateUser createUser,
                        UserResponseAdapter outputAdapter, FindUserById findUserById) {
        this.adapter = adapter;
        this.createUser = createUser;
        this.outputAdapter = outputAdapter;
        this.findUserById = findUserById;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest userRequest) {
        return outputAdapter.adapt(createUser.create(adapter.adapt(userRequest)));
    }

    @ApiImplicitParam(name = "Authorization",
            value = "JWT", required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer jwtHere")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return outputAdapter.adapt(findUserById.find(id));
    }

}
