package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.CardFacade;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

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
    public ResponseEntity<?> getCard(@Valid @Min(1) @PathVariable Long id) {

        return cardFacade.getCard(id);
    }
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllCards() {

        return cardFacade.getAllCards();
    }
    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> addCard(@Valid @RequestBody CardCreateRequestDTO cardCreateRequestDTO) {

        return cardFacade.addCard(cardCreateRequestDTO);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/payment")
    public ResponseEntity<?> payCardDebt(@Valid @RequestBody CardPaymentRequestDTO cardPaymentRequestDTO) {

        return cardFacade.payCardDebt(cardPaymentRequestDTO);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getAllCardOneAccount")
    public ResponseEntity<?> getAllCardOneAccount(@Valid @Min(1) @RequestParam Long id) {

        return cardFacade.getAllCardByAccountNumber(id);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getAllCardOneCustomer")
    public ResponseEntity<?> getAllCardOneCustomer(@Valid @Min(1) @RequestParam Long id) {

        return cardFacade.getAllCardByCustomerId(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@Valid @Min(1) @PathVariable Long id,
                                        @RequestParam(required = false) boolean isHardDelete) {

        return cardFacade.deleteCard(id, isHardDelete);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/balance/{cardNo}")
    public ResponseEntity<?> getBalanceByCardNo(@Valid @Pattern(regexp = "[1-9]\\d{15}") @PathVariable String cardNo) {

        return cardFacade.getCardBalance(cardNo);
    }
}
