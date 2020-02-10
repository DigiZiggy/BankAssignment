package com.swedbank.application.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swedbank.application.exception.ResourceNotFoundException;
import com.swedbank.application.model.Account;
import com.swedbank.application.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @PostMapping("/accounts")
    void addAccount(@RequestBody Account account) {
        accountRepository.save(account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));
        return ResponseEntity.ok().body(account);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long accountId,
                                                 @Valid @RequestBody Account accountDetails) throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        account.setName(accountDetails.getName());
        account.setBalance(accountDetails.getBalance());
        account.setCurrency(accountDetails.getCurrency());
        final Account updatedAccount = accountRepository.save(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/accounts/from/{from_id}/to/{to_id}")
    public ResponseEntity<Account> transferMoney(@PathVariable(value = "from_id") Long fromAccountId,
                                                 @PathVariable(value = "to_id") Long toAccountId,
                                                 @RequestParam BigDecimal amount) throws ResourceNotFoundException {

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + fromAccountId));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + toAccountId));

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);
        final Account updatedAccount = accountRepository.save(fromAccount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        accountRepository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
