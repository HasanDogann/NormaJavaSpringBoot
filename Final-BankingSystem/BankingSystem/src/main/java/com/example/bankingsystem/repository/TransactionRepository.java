package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Override
    Transaction getById(Long id);
}
