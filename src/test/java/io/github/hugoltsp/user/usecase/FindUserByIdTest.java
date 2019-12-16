package io.github.hugoltsp.user.usecase;

import io.github.hugoltsp.user.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FindUserByIdTest {

    @InjectMocks
    FindUserById findUserById;

    @Mock
    Logger logger;

    @Mock
    UserRepository userRepository;

    @Test
    void should() {

        //GIVEN
        long id = 1L;

        //WHEN
        findUserById.find(id);

        //THEN
        verify(userRepository).findById(any());
    }

}