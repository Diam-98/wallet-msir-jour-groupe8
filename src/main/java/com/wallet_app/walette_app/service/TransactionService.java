package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Transaction;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Autowired
    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void makeDeposit(Account account, double amount){
        Transaction transaction = new Transaction();
        account.setBalance(account.getBalance() + amount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setReceiverAccountId(account);
        transaction.setTransactionType("Depot");

        accountRepository.save(account);

        transactionRepository.save(transaction);
    }

    public void makeWithdraw(Account account, double amount){
        Transaction transaction = new Transaction();

        if (account.getBalance() < amount){
            throw new IllegalArgumentException("Le solde de votre compte est insuffisant");
        }

        account.setBalance(account.getBalance() - amount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setSenderAccount(account);
        transaction.setTransactionType("Retrait");

        accountRepository.save(account);

        transactionRepository.save(transaction);
    }

    public void makeTransfer(Account sourceAccount, Account targetAccount, double amount){
        Transaction transaction = new Transaction();

        if (sourceAccount.getBalance() < amount){
            throw new IllegalArgumentException("Le solde de votre compte est insuffisant");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setSenderAccount(sourceAccount);
        transaction.setReceiverAccountId(targetAccount);
        transaction.setTransactionType("Transfert");
        transaction.setTransactionStatus("EFFECTUE");

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        transactionRepository.save(transaction);
    }

    public void cancelTransfer(Transaction transaction){
        Account senderAccount = transaction.getSenderAccount();
        Account receiverAccount = transaction.getReceiverAccount();

        senderAccount.setBalance(senderAccount.getBalance() + transaction.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() - transaction.getAmount());

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        transaction.setTransactionStatus("ANNULE");

        transactionRepository.save(transaction);
    }

    public Transaction findTransaction(Long id){
        return transactionRepository.getTransactionById(id);
    }
}
