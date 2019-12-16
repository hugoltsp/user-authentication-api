package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.orm.User;
import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.presenter.domain.LoginRequest;
import io.github.hugoltsp.user.usecase.domain.UserApiAuthenticationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserLogInTest {

    @InjectMocks
    UserLogIn logIn;

    @Mock
    UserRepository userRepository;

    @Mock
    UserTokenService userTokenService;

    @Mock
    Logger logger;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationService authenticationService;

    @Test
    void login() {
        //GIVEN
        var loginRequest = new LoginRequest();
        loginRequest.setEmail("jhon@mail.com");
        loginRequest.setPassword("9079769");
        User user = new User();
        user.setPassword("9079769");

        //WHEN
        when(userRepository.findByEmail("jhon@mail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        //THEN
        logIn.login(loginRequest);

        verify(userTokenService).invalidateTokens(any());
        verify(userTokenService).createToken(any());
        verify(authenticationService).authenticate(any());
    }

    @Test
    void login_should_throw_exception_when_password_doesnt_match() {
        //GIVEN
        var loginRequest = new LoginRequest();
        loginRequest.setEmail("jhon@mail.com");
        loginRequest.setPassword("9079769");
        User user = new User();
        user.setPassword("907976934er");

        //WHEN
        when(userRepository.findByEmail("jhon@mail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        //THEN
        assertThrows(UserApiAuthenticationException.class,
                () -> logIn.login(loginRequest));

        verify(userTokenService, never()).invalidateTokens(any());
        verify(userTokenService, never()).createToken(any());
        verify(authenticationService, never()).authenticate(any());
    }

}