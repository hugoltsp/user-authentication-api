package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.repository.UserRepository;
import io.github.hugoltsp.user.usecase.domain.UserApiAuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FindUserByIdTest {

    @InjectMocks
    FindUserById findUserById;

    @Mock
    Logger logger;

    @Mock
    UserRepository userRepository;

    @Mock
    AuthenticationService authenticationService;

    @Test
    void find() {

        //GIVEN
        long id = 1L;

        //WHEN
        Mockito.when(authenticationService.getCurrentAuthenticatedUserId()).thenReturn(Optional.of(1L));

        //THEN
        findUserById.find(id);
        verify(userRepository).findById(any());
    }

    @Test
    void find_should_throw_exception_when_id_differs_from_authenticated_user() {

        //GIVEN
        long id = 1L;

        //WHEN
        Mockito.when(authenticationService.getCurrentAuthenticatedUserId()).thenReturn(Optional.of(8L));

        //THEN
        assertThrows(UserApiAuthenticationException.class, () -> findUserById.find(id));

        verify(userRepository, never()).findById(any());
    }

}