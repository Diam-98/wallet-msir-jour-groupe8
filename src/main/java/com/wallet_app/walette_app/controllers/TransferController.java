package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Transfer;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import com.wallet_app.walette_app.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class TransferController {
    private final TransferService transferService;
    private final AccountService accountService;
    @Autowired
    public TransferController(TransferService transferService, AccountService accountService) {
        this.transferService = transferService;
        this.accountService = accountService;
    }
    @PostMapping("/transfer")
    public ResponseEntity<Transfer> makeTransfer(@RequestParam String targetAccountNumber, double amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authentication.getPrincipal();

        Account sourceAccount = user.getAccount();
        Account targetAccount = accountService.searchAccount(targetAccountNumber);

        if (sourceAccount == null || targetAccount == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Transfer transfer = transferService.makeTransfer(sourceAccount, targetAccount, amount);

        return new ResponseEntity<>(transfer, HttpStatus.OK);

    }

}
