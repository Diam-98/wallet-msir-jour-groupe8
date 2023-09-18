package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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
    private Double accountLimit;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "receiverAccount")
    private List<Transaction> incomingTransactions;

    public Account() {
    }

    public Account(User user, String accountId, Double balance, Date creationDate, Double accountLimit) {
        this.user = user;
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate = creationDate;
        this.accountLimit = accountLimit;
    }

    public Long getId() {
        return id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getSold() {
        return balance;
    }

    public void setSold(Double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getLimit() {
        return accountLimit;
    }

    public void setLimit(Double accountLimit) {
        this.accountLimit = accountLimit;
    }
}
