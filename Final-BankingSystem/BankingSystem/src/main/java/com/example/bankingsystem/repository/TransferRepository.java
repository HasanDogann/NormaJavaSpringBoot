package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

    @Override
    Transfer getById(Long id);
}
