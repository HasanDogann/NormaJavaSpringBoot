package com.example.bankingsystem.facade.impl;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.facade.CardFacade;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class CardFacadeImpl implements CardFacade {

    private final CardService cardService;
    private final CardConverter cardConverter;

    @Override
    public ResponseEntity<String> addCard(CardCreateRequestDTO cardCreateRequestDTO) {
        Card card = cardService.addCard(cardCreateRequestDTO);
        if (Objects.isNull(card)) {
            ResponseEntity.badRequest().body("Card could not created!");
        }
        return ResponseEntity.ok().body("Card is created successfully");
    }

    @Override
    public ResponseEntity<CardGetResponseDTO> getCard(Long id) {
        CardGetResponseDTO cardGetResponseDTO = cardConverter
                .toCardResponseFromCard(cardService.getCard(id));
        return ResponseEntity.ok().body(cardGetResponseDTO);
    }

    @Override
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCards() {
        Collection<CardGetResponseDTO> collection = cardService.getAllCards()
                .stream()
                .map(cardConverter::toCardResponseFromCard)
                .toList();
        if (collection.isEmpty()) {
            throw new ServiceOperationNotFoundException.CardNotFoundException("There is no card");
        }

        return ResponseEntity.ok().body(collection);
    }

    @Override
    public ResponseEntity<CardBalanceResponseDTO> getCardBalance(String cardNo) {
        CardBalanceResponseDTO cardBalanceGetResponseDTO = cardConverter
                .toCardBalanceResponseFromCard(cardService.getCardBalance(cardNo));

        return ResponseEntity.ok().body(cardBalanceGetResponseDTO);
    }

    @Override
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCardByAccountNumber(Long id) {
        Collection<CardGetResponseDTO> collection = cardService.getAllCardByAccountNumber(id)
                .stream().map(cardConverter::toCardResponseFromCard).toList();

        return ResponseEntity.ok().body(collection);
    }

    @Override
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCardByCustomerId(Long id) {
        Collection<CardGetResponseDTO> collection = cardService.getAllCardByCustomerId(id)
                .stream().map(cardConverter::toCardResponseFromCard).toList();
        if(collection.isEmpty()){
            return new ResponseEntity("There is No Card!",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(collection);

    }

    @Override
    public ResponseEntity<String> deleteCard(Long id, boolean isHardDelete) {
        String response = cardService.deleteCard(id,isHardDelete);

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<String> payCardDebt(CardPaymentRequestDTO cardPaymentRequestDTO) {

        cardService.payCardDebt(cardPaymentRequestDTO);
        return ResponseEntity.ok().body("Car payment is successful.");
    }
}
