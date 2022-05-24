package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.CardType;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
public record CardGetResponseDTO(String cardNo,
                                 BigDecimal cardDebt,
                                 BigDecimal cardBalance,
                                 String customerName,
                                 String customerSurname,
                                 BigDecimal cardLimit,
                                 CardType cardType,
                                 Long customerID,
                                 Long accountID,
                                 Long cardID) {
}
