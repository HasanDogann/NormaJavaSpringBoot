package com.example.bankingsystem.facade.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.facade.AccountFacade;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 26.05.2022
 */
@Service
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final AccountConverter accountConverter;

    @Override
    public ResponseEntity<String> addAccount(AccountCreateRequestDTO accountCreateRequestDTO) {
        Account account = accountService.addAccount(accountCreateRequestDTO);
        if (Objects.isNull(account)) {
            return ResponseEntity.badRequest().body("Account could not created!");
        }
        return ResponseEntity.ok().body("Account is created successfully");
    }

    @Override
    public ResponseEntity<AccountGetResponseDTO> getAccount(Long id) {

        AccountGetResponseDTO accountGetResponseDTO = accountConverter.convertAccountToResponseDto(accountService.getAccount(id));

        return ResponseEntity.ok().body(accountGetResponseDTO);
    }

    @Override
    public ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccounts() {

        Collection<AccountGetResponseDTO> accountGetResponseDTOList = accountService.getAllAccounts()
                .stream()
                .map(accountConverter::convertAccountToResponseDto)
                .toList();
        if (accountGetResponseDTOList.isEmpty()) {
            throw new ServiceOperationNotFoundException.AccountNotFoundException("No account is found!");
        }
        return ResponseEntity.ok().body(accountGetResponseDTOList);
    }

    @Override
    public ResponseEntity<AccountGetResponseDTO> getAccountByIBAN(String iban) {
        AccountGetResponseDTO accountGetResponseDTO = accountConverter.convertAccountToResponseDto(accountService.getAccountByIBAN(iban));
        return ResponseEntity.ok().body(accountGetResponseDTO);
    }

    @Override
    public ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccountOneCustomer(Long id) {

        Collection<AccountGetResponseDTO> accountCollection = accountService.getAllAccountOneCustomer(id)
                .stream().map(accountConverter::convertAccountToResponseDto).toList();
        if (accountCollection.isEmpty()) {
            throw new ServiceOperationNotFoundException.AccountNotFoundException("No account is found!");
        }
        return ResponseEntity.ok().body(accountCollection);


    }

    @Override
    public ResponseEntity<String> deleteAccount(Long id, boolean isHardDelete) {
        String result = accountService.deleteAccount(id,isHardDelete);
        return ResponseEntity.ok().body(result);
    }
}
