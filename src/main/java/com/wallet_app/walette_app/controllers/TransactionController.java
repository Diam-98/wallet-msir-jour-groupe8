package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Transaction;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import com.wallet_app.walette_app.service.TransactionService;
import com.wallet_app.walette_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService, AccountService accountService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/depot/{userId}")
    public ResponseEntity<?> deposit(@PathVariable Long userId, double amount){
        User user = userService.findUser(userId);
        Account account = user.getAccount();
        transactionService.makeDeposit(account, amount);

        return new ResponseEntity<>(
                "Depot effectue, votre solde actuel est : " + account.getBalance(),
                HttpStatus.OK
        );
    }

    @PostMapping("/retrait/{userId}")
    public ResponseEntity<?> withdraw(@PathVariable Long userId, double amount){
        User user = userService.findUser(userId);
        Account account = user.getAccount();
        transactionService.makeWithdraw(account, amount);

        return new ResponseEntity<>(
                "Retrait effectue, votre solde actuel est : " + account.getBalance(),
                HttpStatus.OK
        );
    }

    @PostMapping("/transfert/{userId}")
    public ResponseEntity<?> transfer(@PathVariable Long userId, String targetAccountId, double amount){
        User user = userService.findUser(userId);
        Account sourceAccount = user.getAccount();
        Account targetAccount = accountService.findAccountByAccountId(targetAccountId);

        transactionService.makeTransfer(sourceAccount, targetAccount, amount);

        return new ResponseEntity<>(
                "Transfert effectue, votre solde actuel est : " + sourceAccount.getBalance(),
                HttpStatus.OK
        );
    }

    @PostMapping("/annuler/{userId}/{transactId}")
    public ResponseEntity<?> cancelTransfer(@PathVariable Long transactId, @PathVariable Long userId){
        Transaction transaction = transactionService.findTransaction(transactId);
        transactionService.cancelTransfer(transaction);
        User user = userService.findUser(userId);
        Account sourceAccount = user.getAccount();

        return new ResponseEntity<>(
                "Transfert annule, votre solde actuel est : " + sourceAccount.getBalance(),
                HttpStatus.OK
        );
    }
}
