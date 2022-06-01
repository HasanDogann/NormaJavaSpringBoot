package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.facade.AccountFacade;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@RestController
@RequestMapping("/api/v2/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountFacade accountFacade;
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @PostMapping
    public ResponseEntity<?> addAccount(@Valid @RequestBody AccountCreateRequestDTO accountCreateRequestDTO) {

        return accountFacade.addAccount(accountCreateRequestDTO);
    }
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/getAccountByCustomerIban")
    public ResponseEntity<?> getAccountByIban(@Valid @Pattern(regexp = "TR\\d{24}") @RequestParam String IBAN) {

       return accountFacade.getAccountByIBAN(IBAN);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount( @Valid @Min(1) @PathVariable Long id) {

        return accountFacade.getAccount(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {

        return accountFacade.getAllAccounts();
    }

    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @GetMapping("/getAllAccountOneCustomer")
    public ResponseEntity<?> getAllAccountOneCustomer(@Valid @Min(1) @RequestParam Long id) {

        return accountFacade.getAllAccountOneCustomer(id);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@Valid @Min(1) @PathVariable Long id,
                                           @Valid @RequestParam(required = false) boolean isHardDelete) {

       return accountFacade.deleteAccount(id,isHardDelete);
    }
}
