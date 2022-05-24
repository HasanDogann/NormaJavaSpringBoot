package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.CardType;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public record CardBalanceResponseDTO(String cardNo,
                                     String customerName,
                                     String customerSurname,
                                     CardType cardType,
                                     BigDecimal cardDebt,
                                     BigDecimal cardBalance) {
}
