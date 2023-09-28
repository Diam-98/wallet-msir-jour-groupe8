package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.AccountService;
import com.wallet_app.walette_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Le controller du User")
public class UserController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService= accountService;
    }

    @Operation(summary = "Creer un user", description = "Retoune un message de creation d'un user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user ajoute avec succes"),
    })
    @PostMapping("/create")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            user.setAccount(null);
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok("User ajoute avec succes : " + createdUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec d'ajout d'un user : " + e.getMessage());
        }
    }

    @Operation(summary = "Chercher un compte", description = "Retourne un compte trouve en fonction du numero de compte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "COmpte trouve avec succes"),
    })
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
