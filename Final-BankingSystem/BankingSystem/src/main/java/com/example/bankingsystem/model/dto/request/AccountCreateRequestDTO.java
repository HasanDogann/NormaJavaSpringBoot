package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceType;

public record AccountCreateRequestDTO(AccountType accountType,
                                      BalanceType balanceType,
                                      Long customerId) {
}
