package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public User createUser(User user){
        return repository.save(user);
    }
}
