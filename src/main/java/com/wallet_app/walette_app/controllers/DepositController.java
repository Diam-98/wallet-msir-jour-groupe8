package com.wallet_app.walette_app.controllers;


import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Deposit;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.DepositService;
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
public class DepositController {
    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Deposit> makeDeposit(@RequestParam double amount){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authentication.getPrincipal();

        Account account = user.getAccount();

        if (account == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Deposit desposit = depositService.makeDeposit(account, amount);

        return new ResponseEntity<>(desposit, HttpStatus.OK);
    }
}
