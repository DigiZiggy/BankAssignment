package com.swedbank.application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;

    @ManyToOne
    private Account sourceAccount;

    @ManyToOne
    private Account targetAccount;

    public Transfer() {
    }

    public Transfer(BigDecimal amount, Account sourceAccount, Account targetAccount) {
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    @Override
    public String toString() {
        return "Transfer{" + "id=" + id + ", amount=" + amount +
                ", sourceAccount=" + sourceAccount.toString() +
                ", targetAccount=" + targetAccount.toString() +
                '}';
    }
}
