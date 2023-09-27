package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String accountId;
    private Double balance;
    private Date creationDate;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "receiverAccount")
    private List<Transaction> incomingTransactions;

    public Account() {
    }

    public Account(User user, String accountId, Double balance, Date creationDate, List<Transaction> outgoingTransactions, List<Transaction> incomingTransactions) {
        this.user = user;
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate = creationDate;
        this.outgoingTransactions = outgoingTransactions;
        this.incomingTransactions = incomingTransactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }
}
