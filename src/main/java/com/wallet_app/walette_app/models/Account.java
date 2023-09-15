package com.wallet_app.walette_app.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "user")
    private User userId;
    private String accountId;
    private Double sold;
    private Date creationDate;
    private Double limit;

    public Account() {
    }

    public Account(User userId, String accountId, Double sold, Date creationDate, Double limit) {
        this.userId = userId;
        this.accountId = accountId;
        this.sold = sold;
        this.creationDate = creationDate;
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }
}
