package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Card;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
public interface CardConverter {

    Card toCard(CardCreateRequestDTO cardCreateRequestDTO);

    CardGetResponseDTO toCardResponseFromCard(Card card);

    CardBalanceResponseDTO toCardBalanceResponseFromCard(Card card);
}
