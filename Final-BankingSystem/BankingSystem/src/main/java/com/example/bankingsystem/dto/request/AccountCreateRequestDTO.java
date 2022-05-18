package com.example.bankingsystem.dto.request;

import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;

public record AccountCreateRequestDTO(AccountType accountType,
                                      BalanceType balanceType,
                                      Long customerId) {
}
