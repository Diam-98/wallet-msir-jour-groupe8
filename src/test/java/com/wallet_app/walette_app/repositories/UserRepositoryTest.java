package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetUserById(){
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.getUserById(id)).thenReturn(user);

        User foundUser = userRepository.getUserById(id);

        assertEquals(user, foundUser);
        verify(userRepository, times(1)).getUserById(id);
    }

    @Test
    void testSave(){
        User user = new User();
        user.setId(3L);
        user.setFirstName("Bob");
        user.setLastName("Marley");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }
}
