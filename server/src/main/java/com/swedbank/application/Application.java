package com.swedbank.application;

import com.swedbank.application.model.Account;
import com.swedbank.application.model.Currency;
import com.swedbank.application.repositories.AccountRepository;
import com.swedbank.application.repositories.TransferRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(AccountRepository accountRepository, TransferRepository transferRepository) {
        return args -> {
            Account eur_account = new Account("Savings", new BigDecimal("12450.098765"), Currency.EUR);
            Account gbp_account = new Account("Debit", new BigDecimal("9732.54252"), Currency.GBP);
            Account aud_account = new Account("Credit", new BigDecimal("423451.986"), Currency.AUD);

            accountRepository.save(eur_account);
            accountRepository.save(gbp_account);
            accountRepository.save(aud_account);
            accountRepository.findAll().forEach(System.out::println);
        };
    }
}
