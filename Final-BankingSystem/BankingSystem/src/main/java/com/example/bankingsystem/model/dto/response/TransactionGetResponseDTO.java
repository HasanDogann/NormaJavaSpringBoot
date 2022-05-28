package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.TransferType;

import java.math.BigDecimal;

public record TransactionGetResponseDTO(String senderIBAN,
                                        String receiverIBAN,
                                        BigDecimal amount,
                                        TransferType transferType,
                                        String transferDate) {
}