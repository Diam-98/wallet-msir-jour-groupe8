package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import com.wallet_app.walette_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAccount(@PathVariable Long userId){
        User user = userService.findUser(userId);

        accountService.createAccount(user);

        return new ResponseEntity<>("Compte cree avec succes",HttpStatus.OK);
    }

    @PostMapping("/sold/{userId}")
    public double viewBalance(@PathVariable Long userId){
        User user = userService.findUser(userId);

        return accountService.viewSold(user);
    }

}
