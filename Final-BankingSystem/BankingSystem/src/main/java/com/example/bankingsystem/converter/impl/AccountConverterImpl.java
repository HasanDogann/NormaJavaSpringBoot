package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AccountConverterImpl implements AccountConverter {
    private final CustomerRepository customerRepository;

    @Override
    public Account convertToAccount(AccountCreateRequestDTO accountCreateRequestDTO) {
        Account account = new Account();
        account.setAccountType(accountCreateRequestDTO.accountType());
        account.setBalanceType(accountCreateRequestDTO.balanceType());
        Long accountNo = new Random().nextLong(1000_000_00, 9999_999_99);
        account.setAccountNumber(accountNo);
        account.setIBAN("TR" + new BigDecimal(new Random().nextLong(1000_000_00, 9999_999_99)) + accountNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        account.setCreationDate(formatter.format(date));
        Customer customer = customerRepository.getById(accountCreateRequestDTO.customerId());
        account.setCustomer(customer);

        return account;
    }

    @Override
    public AccountGetResponseDTO convertAccountToResponseDto(Account account) {


        return new AccountGetResponseDTO(account.getCustomer().getName(),
                account.getBalance(),
                account.getBalanceType(),
                account.getIBAN(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getCreationDate(),
                account.isDeleted(),
                account.getId()
        );
    }


}