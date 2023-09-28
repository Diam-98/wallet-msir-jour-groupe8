package com.wallet_app.walette_app.services;

import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.UserRepository;
import com.wallet_app.walette_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser(){
        User user = new User();
        Long id = 4L;
        user.setId(id);
        user.setFirstName("Diamil");
        user.setLastName("Diallo");
        user.setEmail("diamil@gmail.com");
        user.setPassword("1234");

        when(userRepository.getUserById(id)).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
        verify(userRepository, times(1)).getUserById(id);
        verify(userRepository, times(1)).save(user);
    }



}
