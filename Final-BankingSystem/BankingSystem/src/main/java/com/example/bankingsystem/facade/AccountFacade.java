package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 26.05.2022
 */
public interface AccountFacade {


    ResponseEntity<String> addAccount(AccountCreateRequestDTO accountCreateRequestDTO);

    ResponseEntity<AccountGetResponseDTO> getAccount(Long id);

    ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccounts();

    ResponseEntity<AccountGetResponseDTO> getAccountByIBAN(String iban);

    ResponseEntity<Collection<AccountGetResponseDTO>> getAllAccountOneCustomer(Long id);

    ResponseEntity<String> deleteAccount(Long id, boolean isHardDelete);


}
