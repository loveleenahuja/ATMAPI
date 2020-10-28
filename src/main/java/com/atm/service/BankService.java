package com.atm.service;

import com.atm.model.Account;
import com.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService
{
    @Autowired
    private AccountRepository accountRepo;

    public Boolean depositCash(Account userAccount, double amount){
        double new_amount = amount + userAccount.getBalance();
        userAccount.setBalance(new_amount);
        return trySaving(userAccount);
    }

    public Boolean withdrawCash(Account userAccount, double amount){
        double previous_balance = userAccount.getBalance();
        if(previous_balance >= amount){
            double new_amount = userAccount.getBalance() - amount;
            userAccount.setBalance(new_amount);
            return trySaving(userAccount);
        }
        else{
            return false;
        }
    }

    // Helper
    public Boolean trySaving(Account userAccount){
        try {
            Account updated_account = accountRepo.save(userAccount);
            if(updated_account != null){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
}
