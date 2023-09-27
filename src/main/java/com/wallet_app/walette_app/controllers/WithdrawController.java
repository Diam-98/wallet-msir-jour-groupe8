package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.models.Withdraw;
import com.wallet_app.walette_app.service.WithdrawService;
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
public class WithdrawController {
    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Withdraw> makeWithdraw(@RequestParam double amount){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authentication.getPrincipal();

        Account account = user.getAccount();

        if (account == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Withdraw withdraw = withdrawService.makeWithdraw(account, amount);

        return new ResponseEntity<>(withdraw, HttpStatus.OK);
    }
}
