package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface CardPaymentConverter {

    Card toCardFromATMPaymentDTO(CardPaymentRequestDTO cardPaymentRequestDTO);

    Card toCardFromAccountPaymentDTO(CardPaymentRequestDTO cardPaymentRequestDTO);


}
