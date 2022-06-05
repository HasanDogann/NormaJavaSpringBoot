package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.TransferType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
public record TransactionRequestDTO(
        @Min(value = 1, message = "Amount must be bigger than 0") @NotNull BigDecimal amount,
        @Pattern(regexp = "TR\\d{24}|[1-9]\\d{15}")
        String senderIban,
        @Pattern(regexp = "TR\\d{24}")
        String receiverIban,
        TransferType transferType) {
}
