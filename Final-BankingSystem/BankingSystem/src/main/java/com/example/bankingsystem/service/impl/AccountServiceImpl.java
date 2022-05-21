package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
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
        Account account = accountRepository.findAccountByIBAN(IbanNo);

        if (account.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted");
        }
        return account;
    }

    @Override
    public Collection<Account> getAllAccountOneCustomer(Long id) {

        return accountRepository.
                findAccountByCustomer(customerService.getCustomer(id))
                .stream()
                .filter(accounts -> !accounts.isDeleted())
                .toList();
    }

    @Override
    @Transactional
    public String deleteAccount(Long id, boolean isHardDelete) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.AccountNotFoundException("Account is not found"));
        BigDecimal balance = account.getBalance();
        if (!balance.equals(BigDecimal.ZERO)) {

            Customer customer = customerService.getCustomer(account.getCustomer().getId());
            if (account.isDeleted()) {
                throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account is already deleted");
            }
            if (isHardDelete) {

                customer.removeAccountFromCustomer(Set.of(account));
                accountRepository.removeAccountById(id);
                return "Account is hard deleted  successfully";
            }

            customer.removeAccountFromCustomer(Set.of(account));
            account.setDeleted(true);
            accountRepository.save(account);
            return "Account is soft deleted successfully";
        }
        log.info(account.getBalance().toString());
        throw new ServiceOperationCanNotDeleteException.AccountBalanceNotZero("Account balance is not 0 so you can not delete this account.And balance is {balance}");

    }
}
