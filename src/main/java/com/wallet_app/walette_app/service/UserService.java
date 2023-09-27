package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findUser(Long id){
        return userRepository.getUserById(id);
    }
}
