package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "account")
    private Account senderAccountId;
    @OneToOne(mappedBy = "account")
    private Account receiverAccountId;
    private Double amount;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(Account senderAccountId, Account receiverAccountId, Double amount, Date transactionDate) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Account getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Account senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Account getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Account receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
