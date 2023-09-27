package com.wallet_app.walette_app.controllers;

import com.wallet_app.walette_app.dto.LoginDto;
import com.wallet_app.walette_app.dto.RegisterDto;
import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Role;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.repositories.RoleRepository;
import com.wallet_app.walette_app.repositories.UserRepository;
import com.wallet_app.walette_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;
    private final AccountRepository accountRepository;


    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AccountService accountService, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("Connexion avec succes", HttpStatus.OK);
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<?> userRegister(@RequestBody RegisterDto registerDto){

        User user = new User();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setGender(registerDto.getGender());

        Account account = new Account();
        account.setBalance(0.0);
        account.setCreationDate(new Date());
        account.setAccountNumber("CPT-" + UUID.randomUUID().toString().replace("-", ""));

        user.setAccount(account);

//        Role roles = roleRepository.findByName("ROLE_USER").get();
//        user.setRoles(Collections.singleton(roles));

        accountRepository.save(account);

        userRepository.save(user);

        return new ResponseEntity<>("Inscription succes", HttpStatus.OK);
    }
}
