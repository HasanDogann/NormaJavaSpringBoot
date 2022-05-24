package com.example.bankingsystem.core.payment;

import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 *  24.05.2022
 */
public interface PaymentStrategy {

    void pay(CardPaymentRequestDTO cardPaymentRequestDTO);
}
