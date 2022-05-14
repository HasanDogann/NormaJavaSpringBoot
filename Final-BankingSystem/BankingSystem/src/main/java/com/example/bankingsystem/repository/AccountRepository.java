package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Override
    Account getById(Long id);
}
