package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Withdraw;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.repositories.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public WithdrawService(WithdrawRepository withdrawRepository, AccountRepository accountRepository) {
        this.withdrawRepository = withdrawRepository;
        this.accountRepository = accountRepository;
    }

    public Withdraw makeWithdraw(Account account, double amount){
        if (account.getBalance() < amount){
            throw new IllegalArgumentException("Votre solde est insuffisant");
        }

        Withdraw withdraw = new Withdraw();
        withdraw.setAccount(account);
        withdraw.setAmount(amount);
        withdraw.setDate(new Date());

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        return withdrawRepository.save(withdraw);
    }
}
