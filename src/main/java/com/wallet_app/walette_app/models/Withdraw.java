package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Withdraw() {
    }

    public Withdraw(double amount, Date date, Account account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
