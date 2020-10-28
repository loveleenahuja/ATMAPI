package com.atm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class AccountTest {
    private Account account1;

    @BeforeEach
    public void setUp() throws Exception {
        account1 = new Account("john", "abc", 20, "john", "doe");
    }

    @Test
    public void getUsername() throws Exception{
        assertThat(account1.getUsername()).isEqualTo("john");
    }

    @Test
    void setUsername() {
        account1.setUsername("chandler");
        assertThat(account1.getUsername()).isEqualTo("chandler");
    }

    @Test
    void getPassword() {
        assertThat(account1.getPassword()).isEqualTo("abc");
    }

    @Test
    void setPassword() {
        account1.setPassword("xyz");
        assertThat(account1.getPassword()).isEqualTo("xyz");
    }

    @Test
    void getBalance() {
        assertThat(account1.getBalance()).isEqualTo(20);
    }

    @Test
    void setBalance() {
        account1.setBalance(30);
        assertThat(account1.getBalance()).isEqualTo(30);
    }

    @Test
    void getFirstname() {
        assertThat(account1.getFirstname()).isEqualTo("john");
    }

    @Test
    void setFirstname() {
        account1.setFirstname("chandler");
        assertThat(account1.getFirstname()).isEqualTo("chandler");
    }

    @Test
    void getLastname() {
        assertThat(account1.getLastname()).isEqualTo("doe");
    }

    @Test
    void setLastname() {
        account1.setLastname("smith");
        assertThat(account1.getLastname()).isEqualTo("smith");
    }

}