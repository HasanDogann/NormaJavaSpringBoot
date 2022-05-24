package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;

public record AccountCreateRequestDTO(AccountType accountType,
                                      BalanceCurrencyType balanceCurrencyType,
                                      Integer branchCode,
                                      Long customerId) {
}
