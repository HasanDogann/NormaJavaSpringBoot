package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final CustomerService customerService;

    @Override
    public void addAccount(AccountCreateRequestDTO accountCreateRequestDTO) {
        Account account = accountConverter.convertToAccount(accountCreateRequestDTO);
        accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.AccountNotFoundException("Account is not found"));
        if (account.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted");
        }
        return account;
    }

    @Override
    public Collection<AccountGetResponseDTO> getAllAccounts() {
        return accountRepository.findAccountsByIsDeleted(false).stream().map(accountConverter::convertAccountToResponseDto).toList();
    }

    @Override
    public Account getAccount(String IbanNo) {
       Account account= accountRepository.findAccountByIBAN(IbanNo);

        if(account.isDeleted()){
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted");
        }
        return account;
    }

    @Override
    public Collection<Account> getAllAccountsofOneCustomer(Long id) {

        return accountRepository.findAccountByCustomer(customerService.getCustomer(id)
        ).stream().toList();
    }
}
