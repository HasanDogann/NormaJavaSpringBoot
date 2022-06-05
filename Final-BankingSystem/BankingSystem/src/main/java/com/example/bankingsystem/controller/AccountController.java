package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.AccountFacade;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
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
 * 21.05.2022
 */
@RestController
@RequestMapping("/api/v2/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountFacade accountFacade;

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<String> addAccount(@Valid @RequestBody AccountCreateRequestDTO accountCreateRequestDTO) {
        logger.trace("Post method used for adding new Account");
        return accountFacade.addAccount(accountCreateRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/getAccountByCustomerIban/{iban}")
    public ResponseEntity<AccountGetResponseDTO> getAccountByIban(@Valid @Pattern(regexp = "TR\\d{24}") @PathVariable String iban) {
        logger.trace("Get method used for getting account with IBAN: {}", iban);
        return accountFacade.getAccountByIBAN(iban);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AccountGetResponseDTO> getAccount(@Valid @Min(1) @PathVariable Long id) {
        logger.trace("Get method used for getting Account : {}", id);
        return accountFacade.getAccount(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccounts() {
        logger.trace("Get method used for getting all Accounts");
        return accountFacade.getAllAccounts();
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getAllAccountOneCustomer")
    public ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccountOneCustomer(@Valid @Min(1) @RequestParam Long id) {
        logger.trace("Get method used for getting accounts of Customer: {}", id);
        return accountFacade.getAllAccountOneCustomer(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@Valid @Min(1) @PathVariable Long id,
                                                @Valid @RequestParam(required = false) boolean isHardDelete) {
        logger.trace("Delete method used for deleting Account: {}", id);
        return accountFacade.deleteAccount(id, isHardDelete);
    }

}
