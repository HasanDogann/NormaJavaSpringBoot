package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;

import java.math.BigDecimal;
import java.util.Set;

public record AccountGetResponseDTO(String customerName,
                                    BigDecimal balance,
                                    BalanceCurrencyType balanceCurrencyType,
                                    String ibanNo,
                                    Long accountNo,
                                    AccountType accountType,
                                    String createDate,
                                    Boolean isDelete,
                                    Long accountId,
                                    Set cardList

) {
}
