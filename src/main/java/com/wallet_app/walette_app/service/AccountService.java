package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account searchAccount(String accountId){
        return accountRepository.getAccountByAccountId(accountId);
    }

    public Account deposit(Double amount){
        return accountRepository.deposit(amount);
    }

    public Account withdraw(Double amount){
        return accountRepository.withdraw(amount);
    }

    public Account transfer(Account account, Double amount){
        return accountRepository.transfer(account, amount);
    }

}
