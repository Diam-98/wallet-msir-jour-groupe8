package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authentication.getPrincipal();

        Account createdAccount = accountService.createAccount(user);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        Account currentUserAccount = user.getAccount();
//        Double balance = currentUserAccount.getBalance();
        return new ResponseEntity<>(userDetails.getUsername(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "User Details: " + userDetails.getUsername();
    }
}
