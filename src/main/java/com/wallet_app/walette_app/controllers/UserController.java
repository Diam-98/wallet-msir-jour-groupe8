package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import com.wallet_app.walette_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AccountService accountService;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService= accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            user.setAccount(null);
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok("User added successfully with ID: " + createdUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: " + e.getMessage());
        }
    }

    @GetMapping("/searchAccount")
    public ResponseEntity<?> searchAccount(@RequestParam("accountId") String accountId) {
        try {
            Account account = accountService.findAccountByAccountId(accountId);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

}
