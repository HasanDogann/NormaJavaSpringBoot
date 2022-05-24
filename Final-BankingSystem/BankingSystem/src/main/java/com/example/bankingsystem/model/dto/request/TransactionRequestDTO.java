package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.TransferCurrencyType;
import com.example.bankingsystem.model.entity.enums.TransferType;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public record TransactionRequestDTO(BigDecimal amount, String senderIban, String receiverIban,
                                    TransferType transferType,
                                    TransferCurrencyType transferCurrencyType) {
}
