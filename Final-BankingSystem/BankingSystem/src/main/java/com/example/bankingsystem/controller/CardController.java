package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.CardFacade;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */

@RestController
@RequestMapping("/api/v2/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardFacade cardFacade;

    @GetMapping("/{id}")
    public ResponseEntity<CardGetResponseDTO> getCard(@Valid @Min(1) @PathVariable Long id) {
        logger.trace("Get method used for getting Card: {}", id);
        return cardFacade.getCard(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCards() {
        logger.trace("Get method used for getting all Cards. ");
        return cardFacade.getAllCards();
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<String> addCard(@Valid @RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        logger.trace("Post method used for adding Card. ");
        return cardFacade.addCard(cardCreateRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/payment")
    public ResponseEntity<String> payCardDebt(@Valid @RequestBody CardPaymentRequestDTO cardPaymentRequestDTO) {
        logger.trace("Post method used for paying Card debt. ");
        return cardFacade.payCardDebt(cardPaymentRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getAllCardOneAccount")
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCardOneAccount(@Valid @Min(1) @RequestParam Long id) {
        logger.trace("Get method used for getting all cards of Account: {}", id);
        return cardFacade.getAllCardByAccountNumber(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getAllCardOneCustomer")
    public ResponseEntity<Collection<CardGetResponseDTO>> getAllCardOneCustomer(@Valid @Min(1) @RequestParam Long id) {
        logger.trace("Get method used for getting all cards of Customer: {}", id);
        return cardFacade.getAllCardByCustomerId(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@Valid @Min(1) @PathVariable Long id,
                                             @RequestParam(name = "isHardDelete", required = false) boolean isHardDelete) {
        logger.trace("Delete method used for deleting Card: {}", id);
        return cardFacade.deleteCard(id, isHardDelete);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/balance/{cardNo}")
    public ResponseEntity<CardBalanceResponseDTO> getBalanceByCardNo(@Valid @Pattern(regexp = "[1-9]\\d{15}") @PathVariable String cardNo) {
        logger.trace("Get method used for getting card balance of Card: {}", cardNo);
        return cardFacade.getCardBalance(cardNo);
    }
}
