package com.example.bankingsystem.service.payment;

import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 *  24.05.2022
 */
public interface PaymentStrategy {

    Card pay(CardPaymentRequestDTO cardPaymentRequestDTO);
}
