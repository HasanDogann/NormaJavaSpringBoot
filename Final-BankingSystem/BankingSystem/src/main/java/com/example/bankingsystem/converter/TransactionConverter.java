package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface TransactionConverter {


    Transaction toTransactionByIBAN(TransactionRequestDTO transactionRequestDTO);

    Transaction toTransactionByPurchase(TransactionRequestDTO transactionRequestDTO);
}
