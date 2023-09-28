package com.wallet_app.walette_app.services;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.repositories.AccountRepository;
import com.wallet_app.walette_app.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount(){
        User user = new User();
        Long id = 4L;
        user.setId(id);
        user.setFirstName("Diamil");
        user.setLastName("Diallo");

        Account account= new Account();

        account.setUser(user);
        account.setBalance(100.0);
        account.setCreationDate(new Date());

        when(accountRepository.save(account)).thenReturn(account);

        Account createdAccount = accountService.createAccount(user);

        assertEquals(account, createdAccount);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testFindAccountById(){
        String accountId = "12345678";

        Account account = new Account();

        account.setAccountId(accountId);
        account.setBalance(100.0);

        Account foundAccount = accountService.findAccountByAccountId(accountId);

        assertEquals(account, foundAccount);

    }

    @Test
    void testViewAccountSold(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Account account = new Account();
        account.setUser(user);
        account.setBalance(100.0);
        account.setAccountId("12345678");

        double currentSold = account.getBalance();

        double sold = accountService.viewSold(user);

        assertEquals(currentSold, sold);

    }

}
