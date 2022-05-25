package com.example.bankingsystem.controller;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 25.05.2022
 */
@RestController
@RequestMapping("/api/v2/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> sendMoney(@RequestBody TransactionRequestDTO transactionRequestDTO){
        transactionService.moneyTransfer(transactionRequestDTO);
        return ResponseEntity.ok().body("Transaction is completed successfully");
    }



}
