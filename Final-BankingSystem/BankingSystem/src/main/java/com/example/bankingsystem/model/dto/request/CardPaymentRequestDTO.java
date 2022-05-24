package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.PaymentType;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public record CardPaymentRequestDTO(BigDecimal amount, String cardNo,
                                    PaymentType paymentType) {
}
