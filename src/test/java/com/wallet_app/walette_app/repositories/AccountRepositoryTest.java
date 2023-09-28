package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountRepositoryTest {

    @Mock
    private AccountRepository accountRepository;

    @Test
    void testSaveAccount(){
        User user = new User();

        user.setId(3L);
        user.setFirstName("Bob");
        user.setLastName("Marley");

        Account account = new Account();

        account.setUser(user);
        account.setBalance(1000.0);
        account.setAccountId("12345678");

        when(accountRepository.save(account)).thenReturn(account);

        Account savedAccount = accountRepository.save(account);

        assertEquals(account, savedAccount);
        verify(accountRepository, times(1)).save(account);

    }

}
