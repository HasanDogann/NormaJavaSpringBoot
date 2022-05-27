package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Card;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 28.05.2022
 */
public interface CardFacade {

    ResponseEntity<String> addCard(CardCreateRequestDTO cardCreateRequestDTO);

    ResponseEntity<CardGetResponseDTO> getCard(Long id);

    ResponseEntity<Collection<CardGetResponseDTO>> getAllCards();

    ResponseEntity<CardBalanceResponseDTO> getCardBalance(String cardNo);

    ResponseEntity<Collection<CardGetResponseDTO>> getAllCardByAccountNumber(Long id);

    ResponseEntity<Collection<CardGetResponseDTO>> getAllCardByCustomerId(Long id);

    ResponseEntity<String> deleteCard(Long id, boolean isHardDelete);

    ResponseEntity<String> payCardDebt(CardPaymentRequestDTO cardPaymentRequestDTO);


}
