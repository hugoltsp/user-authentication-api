package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.infra.security.JwtService;
import io.github.hugoltsp.user.usecase.domain.UserApiException;
import io.github.hugoltsp.user.usecase.domain.UserCreationPort;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreateUserTest {

    @InjectMocks
    CreateUser createUser;

    @Mock
    Logger logger;

    @Mock
    JwtService jwtService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserTokenService userTokenService;

    @Mock
    AuthenticationService authenticationService;

    @Captor
    ArgumentCaptor<User> captor;

    @Test
    void create() {
        //GIVEN
        var userCreation = new UserCreationPort(
                "Fulano",
                "fulano@email.com",
                "asduaushd378egdjksjdisojdo",
                emptyList()
        );

        //WHEN
        when(userRepository.findByEmail("fulano@email.com")).thenReturn(Optional.empty());

        //THEN
        createUser.create(userCreation);

        verify(userRepository).save(captor.capture());
        verify(userTokenService).createToken(any());
        verify(authenticationService).authenticate(any());

        assertThat(userCreation.getEmail(), Matchers.is(captor.getValue().getEmail()));
        assertThat(userCreation.getName(), Matchers.is(captor.getValue().getName()));
        assertThat(userCreation.getEncodedPassword(), Matchers.is(captor.getValue().getPassword()));
    }

    @Test
    void create_should_throw_exception_when_theres_already_a_user_same_email() {
        //GIVEN
        var userCreation = new UserCreationPort(
                "Fulano",
                "fulano@email.com",
                "asduaushd378egdjksjdisojdo",
                emptyList()
        );

        //WHEN
        when(userRepository.findByEmail("fulano@email.com")).thenReturn(Optional.of(new User()));

        //THEN
        assertThrows(UserApiException.class,
                () -> createUser.create(userCreation));

        verify(userRepository, never()).save(captor.capture());
        verify(userTokenService, never()).createToken(any());
        verify(authenticationService, never()).authenticate(any());
    }

}