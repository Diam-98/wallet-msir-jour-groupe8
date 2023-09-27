package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "account")
    private User user;
    private String accountNumber;
    private Double balance;
    private Date creationDate;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transfer> outgoingTransfers;

    @OneToMany(mappedBy = "receiverAccount")
    private List<Transfer> incomingTransfers;

    @OneToMany(mappedBy = "account")
    private List<Deposit> deposits;

    @OneToMany(mappedBy = "account")
    private List<Withdraw> withdraws;

    public Account() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public List<Transfer> getOutgoingTransfers() {
        return outgoingTransfers;
    }

    public void setOutgoingTransfers(List<Transfer> outgoingTransfers) {
        this.outgoingTransfers = outgoingTransfers;
    }

    public List<Transfer> getIncomingTransfers() {
        return incomingTransfers;
    }

    public void setIncomingTransfers(List<Transfer> incomingTransfers) {
        this.incomingTransfers = incomingTransfers;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }
}
