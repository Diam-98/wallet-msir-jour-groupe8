package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Transfer;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Transfer makeTransfer(Account sourceAccount, Account targetAccount, double amount){

        if (sourceAccount.getBalance() < amount){
            throw new IllegalArgumentException("Votre solde est insuffisant");
        }

        Transfer transfer = new Transfer();
        transfer.setSenderAccount(sourceAccount);
        transfer.setReceiverAccountId(targetAccount);
        transfer.setAmount(amount);
        transfer.setTransactionDate(new Date());

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        return transferRepository.save(transfer);
    }
}
