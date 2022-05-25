package com.example.bankingsystem.service.transfer;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface TransferStrategy {

    Transaction pay(TransactionRequestDTO transactionRequestDTO);


}
