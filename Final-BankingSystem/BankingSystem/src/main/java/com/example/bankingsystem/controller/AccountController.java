package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.AccountConverter;
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
    private final AccountService accountService;
    private final AccountConverter accountConverter;


    @GetMapping("/getAccountByCustomerIban")
    public ResponseEntity<?> getAccountByIban(@RequestParam String IBAN) {
        Account account = accountService.getAccount(IBAN);
        AccountGetResponseDTO accountGetResponseDTO = accountConverter.convertAccountToResponseDto(account);
        return ResponseEntity.ok().body(accountGetResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        AccountGetResponseDTO accountGetResponseDTO = accountConverter.convertAccountToResponseDto(account);
        return ResponseEntity.ok().body(accountGetResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        Collection<AccountGetResponseDTO> accountGetResponseDTOS = accountService.getAllAccounts();
        return ResponseEntity.ok().body(accountGetResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountCreateRequestDTO accountCreateRequestDTO) {
        accountService.addAccount(accountCreateRequestDTO);
        return ResponseEntity.ok().body("Account is added successfully");
    }

    @GetMapping("/getAllAccountOneCustomer")
    public ResponseEntity<?> getAllAccountOneCustomer(@RequestParam Long id) {
        Collection<Account> accountList = accountService.getAllAccountOneCustomer(id);
        return ResponseEntity.ok().body(accountList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id,
                                           @RequestParam(required = false) boolean isHardDelete) {
         String deleteResult = accountService.deleteAccount(id, isHardDelete);
        return ResponseEntity.ok().body(deleteResult);

    }
}
