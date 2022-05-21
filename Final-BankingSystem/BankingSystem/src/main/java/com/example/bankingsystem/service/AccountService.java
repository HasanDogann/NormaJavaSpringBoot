package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;

import java.util.Collection;

public interface AccountService {

    void addAccount(AccountCreateRequestDTO accountCreateRequestDTO);

    Account getAccount(Long id);

    Collection<AccountGetResponseDTO> getAllAccounts();

    Account getAccount(String IbanNo);

    Collection<Account> getAllAccountOneCustomer(Long id);

    String deleteAccount(Long id,boolean isHardDelete);
}
