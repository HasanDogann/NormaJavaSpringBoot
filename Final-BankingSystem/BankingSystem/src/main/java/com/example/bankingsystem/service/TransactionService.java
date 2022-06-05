package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.PurchaseReceiptCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;

import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
public interface TransactionService {

    void moneyTransfer(TransactionRequestDTO transactionRequestDTO);

    Transaction getTransaction(Long id);

    Collection<Transaction> getPurchaseReceipts(PurchaseReceiptCreateRequestDTO purchaseDTO);
}
