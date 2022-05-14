package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.Card;
import com.example.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

    @Override
    Card getById(Long id);
}
