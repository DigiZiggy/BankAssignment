package com.swedbank.application.controllers;

import java.math.BigDecimal;
import java.util.List;

import com.swedbank.application.exception.ResourceNotFoundException;
import com.swedbank.application.model.Account;
import com.swedbank.application.model.Transfer;
import com.swedbank.application.repositories.AccountRepository;
import com.swedbank.application.repositories.TransferRepository;
import com.swedbank.application.util.TransferConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransferConverter transferConverter;

    @GetMapping("/accounts")
    List<Account> getAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @PostMapping("/accounts/add")
    @ResponseStatus(HttpStatus.CREATED)
    Account addAccount(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PostMapping("/accounts/transfer/{id}")
    void transferMoney(@PathVariable(value = "id") Long targetAccountId,
                       @RequestBody Transfer transfer) throws ResourceNotFoundException {

        Account sourceAccount = transfer.getSourceAccount();
        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new ResourceNotFoundException(targetAccountId));

        transfer.setTargetAccount(targetAccount);

        BigDecimal targetAmount = transferConverter.convertSourceAmountToTarget(
                sourceAccount.getCurrency(), targetAccount.getCurrency(), transfer.getAmount());

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transfer.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().add(targetAmount));

        accountRepository.save(targetAccount);
        accountRepository.save(sourceAccount);
        transferRepository.save(transfer);
    }

    @GetMapping("/accounts/{id}")
    Account getAccountById(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(accountId));
    }

    @PutMapping("/accounts/edit/{id}")
    Account updateAccount(@PathVariable(value = "id") Long accountId, @Valid @RequestBody Account accountDetails)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(accountId));

        account.setName(accountDetails.getName());
        account.setBalance(accountDetails.getBalance());
        account.setCurrency(accountDetails.getCurrency());
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/delete/{id}")
    void deleteAccount(@PathVariable(value = "id") Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
