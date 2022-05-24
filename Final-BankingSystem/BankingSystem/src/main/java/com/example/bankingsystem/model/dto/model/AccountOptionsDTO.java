package com.example.bankingsystem.model.dto.model;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;

public record AccountOptionsDTO(AccountType accountType,
                                BalanceCurrencyType balanceCurrencyType) {
}
