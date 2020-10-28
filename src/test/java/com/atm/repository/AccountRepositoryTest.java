package com.atm.repository;

import com.atm.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountrepo;

    @Autowired
    private TestEntityManager entityManager;

    private Account account1;

    @BeforeEach
    public void setUp() throws Exception {
        account1 = new Account("john", "abc", 20, "john", "doe");
        entityManager.persistAndFlush(account1);
    }

    @Test
    void findAccountByUsername() {
        Account account2 = accountrepo.findAccountByUsername("john");
        assertThat(account2).isEqualTo(account1);
    }

}