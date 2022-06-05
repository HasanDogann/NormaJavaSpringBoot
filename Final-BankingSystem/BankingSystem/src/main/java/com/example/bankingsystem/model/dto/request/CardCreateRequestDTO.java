package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.CardType;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */
public record CardCreateRequestDTO(
        @Column(nullable = false) @Min(value = 1, message = "Account ID must be bigger than 0") Long accountId,
        CardType cardType,
        @Column(nullable = false) @Min(value = 1, message = "Limit must be bigger than 0") BigDecimal limit) {
}
