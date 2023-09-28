package com.wallet_app.walette_app.service;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Méthode pour rechercher un compte par accountId
    public Account findAccountByAccountId(String accountId) {
        Optional<Account> optionalAccount = accountRepository.findByAccountId(accountId);

        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            // Gérer le cas où aucun compte avec cet accountId n'est trouvé
            try {
                throw new AccountNotFoundException("Aucun compte trouvé avec l'ID : " + accountId);
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Account createAccount(User user){

        Account account = new Account();
        account.setBalance(0.0);
        account.setCreationDate(new Date());
        account.setAccountId("CPT-" + UUID.randomUUID().toString().replace("-", ""));
        account.setUser(user);

        return accountRepository.save(account);
    }

    public double viewSold(User user){
        return user.getAccount().getBalance();
    }
}
