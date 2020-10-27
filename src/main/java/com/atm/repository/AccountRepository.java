package com.atm.repository;

import com.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountByUsername(String username);
}
