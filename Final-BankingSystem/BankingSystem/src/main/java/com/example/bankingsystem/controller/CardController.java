package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.facade.CardFacade;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * BankingSystemApplication.java
 * 23.05.2022
 */

@RestController
@RequestMapping("/api/v2/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final CardFacade cardFacade;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@PathVariable Long id) {

        return cardFacade.getCard(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllCards() {

        return cardFacade.getAllCards();
    }

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody CardCreateRequestDTO cardCreateRequestDTO) {

        return cardFacade.addCard(cardCreateRequestDTO);
    }
    @PostMapping("/payment")
    public ResponseEntity<?> payCardDebt(@RequestBody CardPaymentRequestDTO cardPaymentRequestDTO){

        return cardFacade.payCardDebt(cardPaymentRequestDTO);
    }

    @GetMapping("/getAllCardOneAccount")
    public ResponseEntity<?> getAllCardOneAccount(@RequestParam Long id) {

        return cardFacade.getAllCardByAccountNumber(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id,
                                           @RequestParam(required = false) boolean isHardDelete) {

        return cardFacade.deleteCard(id,isHardDelete);
    }

    @GetMapping("/balance/{cardNo}")
    public ResponseEntity<?> getBalanceByCardNo(@PathVariable String cardNo){

        return cardFacade.getCardBalance(cardNo);
    }
}
