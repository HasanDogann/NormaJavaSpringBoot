package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */

@RestController
@RequestMapping("/api/v2/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final CardService cardService;
    private final CardConverter cardConverter;


    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@PathVariable Long id) {
        Card card = cardService.getCard(id);
        CardGetResponseDTO cardGetResponseDTO = cardConverter.toCardResponseFromCard(card);
        return ResponseEntity.ok().body(cardGetResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllCards() {
        Collection<CardGetResponseDTO> cardCollection = cardService.getAllCards()
                .stream()
                .map(cardConverter::toCardResponseFromCard).toList();
        return ResponseEntity.ok().body(cardCollection);
    }

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        cardService.addCard(cardCreateRequestDTO);
        return ResponseEntity.ok().body("Card is added successfully");
    }

    @GetMapping("/getAllAccountOneCustomer")
    public ResponseEntity<?> getAllCardOneAccount(@RequestParam Long id) {
        Collection<CardGetResponseDTO> cardCollection = cardService.getAllCardByAccountNumber(id)
                .stream().map(cardConverter::toCardResponseFromCard).toList();
        return ResponseEntity.ok().body(cardCollection);
    }

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id,
                                           @RequestParam(required = false) boolean isHardDelete) {
        String deleteResult = cardService.deleteAccount(id, isHardDelete);
        return ResponseEntity.ok().body(deleteResult);

    }*/
}
