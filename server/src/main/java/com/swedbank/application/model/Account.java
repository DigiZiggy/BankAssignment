package com.swedbank.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @JsonIgnore
    @OneToMany(mappedBy="sourceAccount", fetch = FetchType.EAGER)
    private Collection<Transfer> transfersDone;

    @JsonIgnore
    @OneToMany(mappedBy="targetAccount", fetch = FetchType.EAGER)
    private Collection<Transfer> transferReceived;

    public Account() {
    }

    public Account(String name, BigDecimal balance, Currency currency) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(long id, String name, BigDecimal balance, Currency currency) {
        this.id = id;
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

    public Collection<Transfer> getTransfersDone() {
        return transfersDone;
    }

    public void setTransfersDone(Collection<Transfer> transfersDone) {
        this.transfersDone = transfersDone;
    }

    public Collection<Transfer> getTransferReceived() {
        return transferReceived;
    }

    public void setTransferReceived(Collection<Transfer> transferReceived) {
        this.transferReceived = transferReceived;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", balance=" + currency+balance + '}';
    }
}
