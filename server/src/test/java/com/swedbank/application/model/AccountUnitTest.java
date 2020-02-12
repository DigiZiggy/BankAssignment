package com.swedbank.application.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountUnitTest {

    @Test
    public void getAccountNameIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        assertThat(account.getName()).isEqualTo("Gold");
    }

    @Test
    public void getAccountBalanceIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        assertThat(account.getBalance()).isEqualTo(new BigDecimal("55555"));
    }

    @Test
    public void getAccountCurrencyIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        assertThat(account.getCurrency()).isEqualTo(Currency.JPY);
    }

    @Test
    public void setName_thenGetNameIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        account.setName("Silver");

        assertThat(account.getName()).isEqualTo("Silver");
    }

    @Test
    public void setBalance_thenGetBalanceIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        account.setBalance(new BigDecimal("111.3456"));

        assertThat(account.getBalance()).isEqualTo(new BigDecimal("111.3456"));
    }

    @Test
    public void setCurrency_thenGetCurrencyIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);

        account.setCurrency(Currency.AUD);

        assertThat(account.getCurrency()).isEqualTo(Currency.AUD);
    }

    @Test
    public void getAccountToStringIsCorrect() {
        Account account = new Account("Gold", new BigDecimal("55555"), Currency.JPY);
        assertThat(account.toString()).isEqualTo("Account{id=0, name=Gold, balance=JPY55555}");
    }
}
