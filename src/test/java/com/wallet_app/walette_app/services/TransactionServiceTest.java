package com.wallet_app.walette_app.services;

import com.wallet_app.walette_app.models.Account;
import com.wallet_app.walette_app.models.Transaction;
import com.wallet_app.walette_app.models.User;
import com.wallet_app.walette_app.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TransactionServiceTest {

    @Mock
    private TransactionService transactionService;

    @Test
    void testTransfer(){
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Sadio");
        user2.setLastName("Mane");

        Account sourceAccount = new Account();
        sourceAccount.setUser(user1);
        sourceAccount.setBalance(1000.0);
        sourceAccount.setAccountId("12345678");

        Account targetAccount = new Account();
        targetAccount.setUser(user2);
        targetAccount.setBalance(1500.0);
        targetAccount.setAccountId("87654321");

        transactionService.makeTransfer(sourceAccount, targetAccount, 500.0);
    }
}
