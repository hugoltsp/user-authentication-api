package io.github.hugoltsp.user.presenter;

import io.github.hugoltsp.user.presenter.domain.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserEndpoint {

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().build();
    }

}
