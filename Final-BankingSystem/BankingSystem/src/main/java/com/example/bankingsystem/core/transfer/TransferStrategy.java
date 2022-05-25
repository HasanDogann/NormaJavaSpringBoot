package com.example.bankingsystem.core.transfer;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface TransferStrategy {

    Transaction pay(TransactionRequestDTO transactionRequestDTO);


}
