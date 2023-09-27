package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Deposit;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepositService {
    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, AccountRepository accountRepository) {
        this.depositRepository = depositRepository;
        this.accountRepository = accountRepository;
    }

    public Deposit makeDeposit(Account account, double amount){
        Deposit deposit = new Deposit();
        deposit.setAccount(account);
        deposit.setAmount(amount);
        deposit.setDate(new Date());

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        return depositRepository.save(deposit);
    }

}
