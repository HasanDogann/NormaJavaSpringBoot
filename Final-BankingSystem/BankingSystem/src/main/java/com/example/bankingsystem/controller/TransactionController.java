
package com.example.bankingsystem.controller;


import com.example.bankingsystem.facade.TransactionFacade;
import com.example.bankingsystem.model.dto.request.PurchaseReceiptCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.dto.response.PurchaseReceiptGetResponseDTO;
import com.example.bankingsystem.model.dto.response.TransactionGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;


/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */

@RestController
@RequestMapping("/api/v2/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionFacade transactionFacade;

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<String> sendMoney(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        logger.trace("Post method used for sending Money ");
        return transactionFacade.sendMoney(transactionRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionGetResponseDTO> getTransaction(@Valid @Min(1) @PathVariable Long id) {
        logger.trace("Get method used for getting Transaction: {}", id);
        return transactionFacade.getTransaction(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/receipts")
    public ResponseEntity<Collection<PurchaseReceiptGetResponseDTO>> getPurchaseReceipts(@Valid @RequestBody PurchaseReceiptCreateRequestDTO requestDTO) {
        logger.trace("Post method used for getting Purchases.:");
        return transactionFacade.getPaymentReceipts(requestDTO);
    }


}



