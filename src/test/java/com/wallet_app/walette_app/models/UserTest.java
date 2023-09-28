package com.wallet_app.walette_app.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testConstructorAndGetter(){
        Long id = 1L;
        String firstName = "Jean";
        String lastName = "Paul";
        String email = "jean@gmail.com";
        String password = "1234";
        String phone = "771234567";
        String gender = "Homme";

        User user = new User(id, firstName, lastName, email, password, phone, gender);

        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(gender, user.getGender());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testSetterAndGetter(){

        User user = new User();

        user.setFirstName("Jeane");
        user.setLastName("Pauline");
        user.setEmail("jeane@gmail.com");
        user.setPhone("771234567");
        user.setPassword("1234");
        user.setGender("Homme");

        assertEquals("Jeane", user.getFirstName());
        assertEquals("Pauline", user.getLastName());
        assertEquals("jeane@gmail.com", user.getEmail());
        assertEquals("771234567", user.getPhone());
        assertEquals("Homme", user.getGender());
        assertEquals("1234", user.getPassword());
    }

}
