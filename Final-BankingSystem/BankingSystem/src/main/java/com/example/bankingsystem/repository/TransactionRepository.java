package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.TransferType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Override
    Transaction getById(Long id);


    Collection<Transaction> getTransactionsByTransferTypeIsAndSenderIBANIsAndTransferDateIsGreaterThan(TransferType transferType,String senderIban,String Date);
}
