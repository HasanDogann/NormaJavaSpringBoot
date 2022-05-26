package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.facade.AccountFacade;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v2/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountFacade accountFacade;

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountCreateRequestDTO accountCreateRequestDTO) {

        return accountFacade.addAccount(accountCreateRequestDTO);
    }

    @GetMapping("/getAccountByCustomerIban")
    public ResponseEntity<?> getAccountByIban(@RequestParam String IBAN) {
       return accountFacade.getAccountByIBAN(IBAN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
       return accountFacade.getAccount(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return accountFacade.getAllAccounts();
    }


    @GetMapping("/getAllAccountOneCustomer")
    public ResponseEntity<?> getAllAccountOneCustomer(@RequestParam Long id) {
      return accountFacade.getAllAccountOneCustomer(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id,
                                           @RequestParam(required = false) boolean isHardDelete) {
       return accountFacade.deleteAccount(id,isHardDelete);
    }
}
