package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationBlockedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.enums.PaymentType;
import com.example.bankingsystem.repository.CardRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CardService;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.payment.PaymentStrategy;
import com.example.bankingsystem.service.payment.strategies.CreditCardPaymentByATM;
import com.example.bankingsystem.service.payment.strategies.CreditCardPaymentByAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 23.05.2022
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CustomerService customerService;
    private final CardRepository cardRepository;
    private final CardConverter cardConverter;
    private PaymentStrategy paymentStrategy;
    private final AccountService accountService;


    @Override
    public Card addCard(CardCreateRequestDTO cardCreateRequestDTO) {
        Card card = cardConverter.toCard(cardCreateRequestDTO);
        cardRepository.save(card);
        return card;
    }

    @Override
    public Card getCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.CardNotFoundException("Card is not found"));
        if (card.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException("Card was deleted!");
        }
        return card;
    }

    @Override
    public Collection<Card> getAllCards() {

        return cardRepository.findAll()
                .stream()
                .filter(c -> !c.isDeleted())
                .toList();

    }

    @Override
    public Card getCardBalance(String cardNo) {
        Card card = cardRepository.getCardByCardNo(cardNo);
        if (Objects.isNull(card)) {
            throw new ServiceOperationNotFoundException.CardNotFoundException("Card is not found");
        }
        if (card.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException("Card was deleted");
        }
        if (card.isBlocked()) {
            throw new ServiceOperationBlockedException.CardBlockedException("Card was blocked");
        }
        return card;
    }

    @Override
    public Collection<Card> getAllCardByAccountNumber(Long id) {
        Account account = accountService.getAccount(id);
        Collection<Card> cardCollection = cardRepository.findAll()
                .stream()
                .filter(a -> a.getAccount().getId().equals(id))
                .toList();
        if (Objects.isNull(cardCollection) || cardCollection.isEmpty()) {
            throw new ServiceOperationNotFoundException.CardNotFoundException("There is no card at this account");
        }
        return cardCollection;
    }

    @Override
    public Collection<Card> getAllCardByCustomerId(Long id) {
        Customer customer = customerService.getCustomer(id);
        Collection<Card> cardCollection = cardRepository.getAllByCustomerId(customer.getId());
        if (Objects.isNull(cardCollection)) {
            throw new ServiceOperationNotFoundException.CardNotFoundException("There is no card on this customer");
        }
        return cardCollection;
    }

    @Override
    public String deleteCard(Long id, boolean isHardDelete) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.CardNotFoundException("Card is not found!"));
        if (card.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException("Card was deleted");
        }
        boolean hasCardDebt = getCard(id).getCardDebt().compareTo(BigDecimal.ZERO) > 0;
        boolean hasCardBalance = getCard(id).getCardBalance().compareTo(BigDecimal.ZERO) > 0;

        if (!hasCardBalance && !hasCardDebt) {
            if (!isHardDelete) {
                card.setDeleted(true);
                cardRepository.save(card);
                return "Card is soft deleted successfully";
            }
            cardRepository.deleteById(id);
            return "Card is hard deleted successfully";
        }

        return "You can not delete card because balance or debt is not zero!";

    }

    @Override
    public void payCardDebt(CardPaymentRequestDTO cardPaymentRequestDTO) {
        if (cardPaymentRequestDTO.paymentType().equals(PaymentType.BY_ACCOUNT)) {
            paymentStrategy = new CreditCardPaymentByAccount(cardRepository);
        } else if (cardPaymentRequestDTO.paymentType().equals(PaymentType.BY_ATM)) {
            paymentStrategy = new CreditCardPaymentByATM(cardRepository);
        }

        paymentStrategy.pay(cardPaymentRequestDTO);

    }


}
