package com.atm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@Entity
public class Account {

    @Id
    @NotEmpty
    @Column(unique=true)
    private String username;

    @NotEmpty
    private String password;

    private double balance = 0.0; // Set a default value for balance on account creation

    private String firstname;
    private String lastname;

    @Version
    private long version;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Account(){
        super();
    }

    public Account(String username, String password, double balance, String firstname, String lastname){
        super();
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
