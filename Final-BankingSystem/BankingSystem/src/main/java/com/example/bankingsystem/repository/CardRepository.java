package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Override
    Card getById(Long id);

    Card getCardByCardNo(String cardNo);

    Collection<Card> getAllByCustomerId(Long id);

}
