package com.example.bankingsystem.core.payment.strategies;

import com.example.bankingsystem.converter.CardPaymentConverter;
import com.example.bankingsystem.core.payment.PaymentStrategy;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
@Component
@RequiredArgsConstructor
public class CreditCardPaymentByATM implements PaymentStrategy {

    private final CardPaymentConverter cardPaymentConverter;


    @Override
    public void pay(CardPaymentRequestDTO cardPaymentRequestDTO) {
     cardPaymentConverter.toCardFromATMPaymentDTO(cardPaymentRequestDTO);
    }
}
