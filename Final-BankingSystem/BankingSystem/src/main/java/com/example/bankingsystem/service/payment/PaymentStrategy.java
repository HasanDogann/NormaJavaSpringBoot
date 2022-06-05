package com.example.bankingsystem.service.payment;

import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
public interface PaymentStrategy {

    Card pay(CardPaymentRequestDTO cardPaymentRequestDTO);
}
