package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceType;

import java.math.BigDecimal;
import java.util.Set;

public record AccountGetResponseDTO(String customerName,
                                    BigDecimal balance,
                                    BalanceType balanceType,
                                    String ibanNo,
                                    Long accountNo,
                                    AccountType accountType,
                                    String createDate,
                                    Boolean isDelete,
                                    Long accountId,
                                    Set cardList

) {
}
