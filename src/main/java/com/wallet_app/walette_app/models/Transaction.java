package com.wallet_app.walette_app.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    @ColumnDefault("null")
    private Account senderAccount;
    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    @ColumnDefault("null")
    private Account receiverAccount;
    private Double amount;
    private Date transactionDate;
    private String transactionType;
    @ColumnDefault("null")
    private String transactionStatus;

    public Transaction() {
    }

    public Transaction(Account senderAccount, Account receiverAccount, Double amount, Date transactionDate, String transactionStatus) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccountId(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
