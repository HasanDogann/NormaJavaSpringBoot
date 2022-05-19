package com.example.bankingsystem.service;

import com.example.bankingsystem.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;

import java.util.Collection;

public interface AccountService {

    void addAccount(AccountCreateRequestDTO accountCreateRequestDTO);

    Account getAccount(Long id);

    Collection<AccountGetResponseDTO> getAllAccounts();

    Account getAccount(String IbanNo);

    Collection<Account> getAllAccountsofOneCustomer(Long id);
}
