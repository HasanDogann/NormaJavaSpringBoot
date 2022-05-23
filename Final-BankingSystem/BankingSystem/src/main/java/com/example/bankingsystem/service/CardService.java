package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
public interface CardService {

    void addCard(CardCreateRequestDTO cardCreateRequestDTO);

    Card getCard(Long id);

    Collection<Card> getAllCards();


    Collection<Card> getAllCardByAccountNumber(Long id);

    String deleteCard(Long id,boolean isHardDelete);


}
