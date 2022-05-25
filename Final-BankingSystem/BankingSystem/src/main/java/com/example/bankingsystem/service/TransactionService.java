package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface TransactionService {

    void moneyTransfer(TransactionRequestDTO transactionRequestDTO);

    Transaction getTransaction(Long id);


}
