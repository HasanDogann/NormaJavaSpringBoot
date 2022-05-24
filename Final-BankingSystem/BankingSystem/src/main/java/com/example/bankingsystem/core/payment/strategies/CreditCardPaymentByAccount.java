package com.example.bankingsystem.core.payment.strategies;

import com.example.bankingsystem.converter.CardPaymentConverter;
import com.example.bankingsystem.core.payment.PaymentStrategy;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 24.05.2022
 */
@Component
@RequiredArgsConstructor
public class CreditCardPaymentByAccount implements PaymentStrategy {

    private final CardPaymentConverter cardPaymentConverter;

    @Override
    public void pay(CardPaymentRequestDTO cardPaymentRequestDTO) {
        cardPaymentConverter.toCardFromAccountPaymentDTO(cardPaymentRequestDTO);
    }
}
