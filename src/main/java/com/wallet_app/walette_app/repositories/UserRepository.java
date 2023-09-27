package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
