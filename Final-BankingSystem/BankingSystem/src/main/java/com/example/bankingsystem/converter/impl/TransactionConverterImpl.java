package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.TransactionConverter;
import com.example.bankingsystem.model.dto.response.PurchaseReceiptGetResponseDTO;
import com.example.bankingsystem.model.dto.response.TransactionGetResponseDTO;
import com.example.bankingsystem.model.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionConverterImpl implements TransactionConverter {


    @Override
    public TransactionGetResponseDTO toTransactionResponse(Transaction transaction) {

        return new TransactionGetResponseDTO(transaction.getSenderIBAN(), transaction.getReceiverIBAN(),
                transaction.getTransferAmount(), transaction.getTransferType(), transaction.getTransferDate());
    }

    @Override
    public PurchaseReceiptGetResponseDTO toPurchaseResponse(Transaction transaction) {
        return new PurchaseReceiptGetResponseDTO(transaction.getSenderIBAN(), transaction.getTransferDate(), transaction.getTransferAmount(), transaction.getReceiverIBAN());
    }
}
