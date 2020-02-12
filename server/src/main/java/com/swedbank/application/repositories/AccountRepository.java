package com.swedbank.application.repositories;

import com.swedbank.application.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AccountRepository extends JpaRepository<Account, Long> {}
