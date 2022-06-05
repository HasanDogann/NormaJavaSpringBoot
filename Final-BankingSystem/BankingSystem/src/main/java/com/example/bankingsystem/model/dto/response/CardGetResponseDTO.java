package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.CardType;

import java.math.BigDecimal;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */
public record CardGetResponseDTO(Long cardID,
                                 String cardNo,
                                 BigDecimal cardDebt,
                                 BigDecimal cardBalance,
                                 BigDecimal cardLimit,
                                 CardType cardType,
                                 String customerName,
                                 String customerSurname,
                                 Long customerID,
                                 Long accountID) {
}
