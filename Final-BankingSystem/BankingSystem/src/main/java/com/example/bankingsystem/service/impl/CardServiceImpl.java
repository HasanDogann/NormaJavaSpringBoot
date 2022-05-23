package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.repository.CardRepository;
import com.example.bankingsystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    @Override
    public void addCard(CardCreateRequestDTO cardCreateRequestDTO) {
        Card card = cardConverter.toCard(cardCreateRequestDTO);
        cardRepository.save(card);
    }

    @Override
    public Card getCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.CardNotFoundException("Card is not found"));
        if (card.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException("Card was deleted");
        }
        return card;
    }

    @Override
    public Collection<Card> getAllCards() {

        return cardRepository.findAll()
                .stream()
                .toList();

    }

    @Override
    public Collection<Card> getAllCardByAccountNumber(Long id) {

        return cardRepository.findAll()
                .stream()
                .filter(i -> i.getAccount().getId().equals(id))
                .toList();


    }

    @Override
    public String deleteCard(Long id, boolean isHardDelete) {
        return null;
    }
}
