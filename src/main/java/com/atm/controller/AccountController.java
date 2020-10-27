package com.atm.controller;

import com.atm.exception.ExceptionResponse;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.atm.repository.AccountRepository;
import com.atm.model.Account;

import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @PostMapping("/account/create")
    public ResponseEntity<Object> createAccount(@RequestBody @Valid Account account) throws Exception{
        Account new_account = accountRepo.save(account);
        return new ResponseEntity<>(new_account, HttpStatus.OK);
    }

    @PostMapping("/account/deposit/{amount}")
    public ResponseEntity<Object> deposit(@PathVariable("amount") double amount, @RequestBody @Valid Account account) throws Exception{
        return balanceValidate(0, amount, account);
    }

    @PostMapping("/account/withdraw/{amount}")
    public ResponseEntity<Object> withdraw(@PathVariable("amount") double amount, @RequestBody @Valid Account account) throws Exception{
        return balanceValidate(1, amount, account);
    }

    @PostMapping("/account/enquiry")
    public ResponseEntity enquiry(@RequestBody @Valid Account account) throws Exception{
        return balanceValidate(2, 0, account);
    }

    // ************************** Helper Function ****************************************

    /*
        Accepted Parameters:
            Status: 0,1, or 2. 0 means deposit. 1 means withdraw. 2 means enquiry.
            Amount: amount to be deposited or withdrawn.
            account: account object from the request.
     */
    public ResponseEntity<Object> balanceValidate(int status, double amount, Account account){
        Account temp_account = accountRepo.findAccountByUsername(account.getUsername());
        if( temp_account!= null ){             //Account with this username found
            if(account.getPassword().equals(temp_account.getPassword())){
                // *********** Deposit **************
                if(status == 0){
                    double new_amount = amount + temp_account.getBalance();
                    temp_account.setBalance(new_amount);
                    Account updated_account = accountRepo.save(temp_account);
                    return new ResponseEntity<>(updated_account, HttpStatus.OK);
                }

                // *********** Withdraw **************
                else if(status == 1){
                    // Withdraw
                    double previous_balance = temp_account.getBalance();
                    if(previous_balance >= amount){
                        double new_amount = temp_account.getBalance() - amount;
                        temp_account.setBalance(new_amount);
                        Account updated_account;
                        updated_account = accountRepo.save(temp_account);
                        return new ResponseEntity<>(updated_account, HttpStatus.OK);
                    }
                    else{
                        return new ExceptionResponse("Insufficient Balance", HttpStatus.BAD_REQUEST).build(); // Error
                    }
                }

                // *********** Enquire **************
                else if(status == 2){
                    return new ResponseEntity<>(temp_account, HttpStatus.OK);
                }
            }
            else{
                return new ExceptionResponse("Incorrect Password", HttpStatus.BAD_REQUEST).build(); // Error
            }
        }
        else {
            return new ExceptionResponse("No user found with this username", HttpStatus.BAD_REQUEST).build(); // Error
        }

        return new ExceptionResponse("Some error occured", HttpStatus.BAD_REQUEST).build(); // Error
    }

}
