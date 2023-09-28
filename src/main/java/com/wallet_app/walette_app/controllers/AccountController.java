package com.wallet_app.walette_app.controllers;

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
@RequestMapping("/api/account")
@Tag(name = "Le controller du comte")
public class AccountController {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Operation(summary = "Creation d'un compte a l'aide du userId", description = "Retourne un message de creation de compte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte cree avec succes"),
    })
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAccount(@PathVariable Long userId){
        User user = userService.findUser(userId);

        accountService.createAccount(user);

        return new ResponseEntity<>("Compte cree avec succes",HttpStatus.OK);
    }

    @Operation(summary = "Verification de solde d'un compte a l'aide du userId", description = "Retourne le sode du compte du user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "le solde du compte"),
    })
    @GetMapping("/sold/{userId}")
    public double viewBalance(@PathVariable Long userId){
        User user = userService.findUser(userId);

        return accountService.viewSold(user);
    }

}
