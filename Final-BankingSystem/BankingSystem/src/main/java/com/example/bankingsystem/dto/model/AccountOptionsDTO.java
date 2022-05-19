package com.example.bankingsystem.dto.model;

import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;

public record AccountOptionsDTO(AccountType accountType,
                                BalanceType balanceType) {
}
