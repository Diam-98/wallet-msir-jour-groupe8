package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);
        account.setCreationDate(new Date());
        account.setAccountNumber("CPT-" + UUID.randomUUID().toString().replace("-", ""));

        return accountRepository.save(account);
    }

    public Account searchAccount(String accountNumber){
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }

}
