package com.wallet_app.walette_app.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    void testConstructorSetterAndGetter(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Account account = new Account();

        account.setUser(user);
        account.setBalance(1000.0);
        account.setCreationDate(new Date());

        assertEquals(user, account.getUser());
        assertEquals(1000.0, account.getBalance());
    }
}
