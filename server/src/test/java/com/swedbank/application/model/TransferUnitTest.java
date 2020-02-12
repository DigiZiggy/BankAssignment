package com.swedbank.application.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferUnitTest {

    @Test
    public void getTransferAmountIsCorrect() {
        Transfer transfer = new Transfer(new BigDecimal("100"),
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));

        assertThat(transfer.getAmount()).isEqualTo(new BigDecimal("100"));
    }

    @Test
    public void getSourceAccountIsCorrect() {
        Account sourceAccount = new Account("Gold", new BigDecimal("5000"), Currency.EUR);
        Account targetAccount = new Account("Silver", new BigDecimal("222"), Currency.USD);
        Transfer transfer = new Transfer(new BigDecimal("100"), sourceAccount, targetAccount);

        assertThat(transfer.getSourceAccount()).isEqualTo(sourceAccount);
    }

    @Test
    public void getTargetAccountIsCorrect() {
        Account sourceAccount = new Account("Gold", new BigDecimal("5000"), Currency.EUR);
        Account targetAccount = new Account("Silver", new BigDecimal("222"), Currency.USD);
        Transfer transfer = new Transfer(new BigDecimal("100"), sourceAccount, targetAccount);

        assertThat(transfer.getTargetAccount()).isEqualTo(targetAccount);
    }

    @Test
    public void setAmount_thenGetAmountIsCorrect() {
        Transfer transfer = new Transfer(new BigDecimal("100"),
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));

        transfer.setAmount(new BigDecimal("9999"));

        assertThat(transfer.getAmount()).isEqualTo(new BigDecimal("9999"));
    }

    @Test
    public void setTargetAccount_thenGetTargetAccountIsCorrect() {
        Account newTargetAccount = new Account("DEBIT", new BigDecimal("9999"), Currency.JPY);

        Transfer transfer = new Transfer(new BigDecimal("100"),
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));

        transfer.setTargetAccount(newTargetAccount);

        assertThat(transfer.getTargetAccount()).isEqualTo(newTargetAccount);
    }

    @Test
    public void setSourceAccount_thenGetSourceAccountIsCorrect() {
        Account newSourceAccount = new Account("DEBIT", new BigDecimal("9999"), Currency.JPY);

        Transfer transfer = new Transfer(new BigDecimal("100"),
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));

        transfer.setSourceAccount(newSourceAccount);

        assertThat(transfer.getSourceAccount()).isEqualTo(newSourceAccount);
    }

    @Test
    public void getTransferToStringIsCorrect() {
        Transfer transfer = new Transfer(new BigDecimal("100"),
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));
        assertThat(transfer.toString()).isEqualTo("Transfer{id=0, amount=100, " +
                "sourceAccount=Account{id=0, name=Gold, balance=EUR5000}, " +
                "targetAccount=Account{id=0, name=Silver, balance=USD222}}");
    }
}
