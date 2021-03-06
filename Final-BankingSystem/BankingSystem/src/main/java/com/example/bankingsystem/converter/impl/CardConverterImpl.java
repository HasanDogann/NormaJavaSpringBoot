package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.CardBalanceResponseDTO;
import com.example.bankingsystem.model.dto.response.CardGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */
@Component
@RequiredArgsConstructor
public class CardConverterImpl implements CardConverter {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Card toCard(CardCreateRequestDTO cardCreateRequestDTO) {
        Card card = new Card();
        card.setCardNo(ConstantUtils.getRandomCardNo());
        card.setCardType(cardCreateRequestDTO.cardType());
        card.setCardLimit(cardCreateRequestDTO.limit());
        Account account = accountRepository.getById(cardCreateRequestDTO.accountId());
        Customer customer = customerRepository.getById(account.getCustomer().getId());
        card.setAccount(account);
        card.setCustomer(customer);
        account.addCardToAccount(Set.of(card));
        customer.addAccountToCustomer(Set.of(account));
        return card;
    }

    @Override
    public CardGetResponseDTO toCardResponseFromCard(Card card) {
        return new CardGetResponseDTO(card.getId(), card.getCardNo(), card.getCardDebt(),
                card.getCardBalance(), card.getCardLimit(), card.getCardType(),
                card.getCustomer().getName(),
                card.getCustomer().getSurname(),
                card.getCustomer().getId(),
                card.getAccount().getId());
    }

    @Override
    public CardBalanceResponseDTO toCardBalanceResponseFromCard(Card card) {
        return new CardBalanceResponseDTO(
                card.getCardNo(), card.getCustomer().getName(),
                card.getCustomer().getSurname(), card.getCardType(),
                card.getCardDebt(),
                card.getCardBalance(),card.getCardLimit()
        );
    }
}
