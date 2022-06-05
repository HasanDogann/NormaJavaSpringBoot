package com.example.bankingsystem.facade.impl;


import com.example.bankingsystem.converter.TransactionConverter;
import com.example.bankingsystem.facade.TransactionFacade;
import com.example.bankingsystem.model.dto.request.PurchaseReceiptCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.dto.response.PurchaseReceiptGetResponseDTO;
import com.example.bankingsystem.model.dto.response.TransactionGetResponseDTO;
import com.example.bankingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 28.05.2022
 */
@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {


    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;


    @Override

    public ResponseEntity<String> sendMoney(TransactionRequestDTO transactionRequestDTO) {
        transactionService.moneyTransfer(transactionRequestDTO);

        return ResponseEntity.ok().body("Transaction is completed successfully");

    }


    @Override

    public ResponseEntity<TransactionGetResponseDTO> getTransaction(Long id) {
        TransactionGetResponseDTO responseDTO = transactionConverter
                .toTransactionResponse(transactionService.getTransaction(id));
        if (Objects.isNull(responseDTO)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @Override
    public ResponseEntity<Collection<PurchaseReceiptGetResponseDTO>> getPaymentReceipts(PurchaseReceiptCreateRequestDTO receiptCreateDTO) {
        Collection<PurchaseReceiptGetResponseDTO> responseDTO = transactionService.getPurchaseReceipts(receiptCreateDTO)
                .stream().map(transactionConverter::toPurchaseResponse).toList();

        return ResponseEntity.ok().body(responseDTO);
    }

}

