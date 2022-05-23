package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.CardType;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
public record CardCreateRequestDTO(Long accountId,
                                   CardType cardType,
                                   BigDecimal limit) {
}
