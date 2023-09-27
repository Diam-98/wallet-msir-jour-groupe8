package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountByAccountNumber(String accountNumber);
}
