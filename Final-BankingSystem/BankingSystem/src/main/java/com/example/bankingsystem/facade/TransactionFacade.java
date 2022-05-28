package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.dto.response.TransactionGetResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 28.05.2022
 */
public interface TransactionFacade {

    ResponseEntity<String> sendMoney(TransactionRequestDTO transactionRequestDTO);

    ResponseEntity<TransactionGetResponseDTO> getTransaction(Long id);
}
