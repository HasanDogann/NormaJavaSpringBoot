package com.example.bankingsystem.dto.response;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;

import java.math.BigDecimal;

public record AccountGetResponseDTO(String customerName,
                                    BigDecimal balance,
                                    BalanceType balanceType,
                                    String ibanNo,
                                    Long accountNo,
                                    AccountType accountType,
                                    String createDate,
                                    Boolean isDelete,
                                    Long accountId

) {
}
