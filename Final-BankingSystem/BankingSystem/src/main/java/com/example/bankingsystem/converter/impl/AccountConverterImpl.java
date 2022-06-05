package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */

@Component
@RequiredArgsConstructor
public class AccountConverterImpl implements AccountConverter {
    private final CustomerRepository customerRepository;

    @Override
    public Account convertToAccount(AccountCreateRequestDTO accountCreateRequestDTO) {
        Account account = new Account();
        account.setAccountType(accountCreateRequestDTO.accountType());
        account.setBalanceCurrencyType(accountCreateRequestDTO.balanceCurrencyType());
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(accountCreateRequestDTO.branchCode());
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        Customer customer = customerRepository.getById(accountCreateRequestDTO.customerId());
        account.setCustomer(customer);

        return account;
    }

    @Override
    public AccountGetResponseDTO convertAccountToResponseDto(Account account) {


        return new AccountGetResponseDTO(account.getId(), account.getCustomer().getName(),
                account.getBalance(),
                account.getBalanceCurrencyType(),
                account.getIBAN(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getCreationDate(),
                account.isDeleted(),
                account.getCardList()
        );
    }


}
