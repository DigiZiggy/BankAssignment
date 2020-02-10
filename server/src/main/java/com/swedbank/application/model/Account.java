package com.swedbank.application.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Account() {
        this.name = "";
        this.balance = BigDecimal.ZERO;
    }

    public Account(String name, BigDecimal balance, Currency currency) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", balance=" + currency+balance + '}';
    }
}
