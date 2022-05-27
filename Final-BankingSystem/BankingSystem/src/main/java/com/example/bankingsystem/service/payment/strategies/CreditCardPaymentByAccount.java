package com.example.bankingsystem.service.payment.strategies;

import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CardRepository;
import com.example.bankingsystem.service.payment.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 24.05.2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CreditCardPaymentByAccount implements PaymentStrategy {

    private final CardRepository cardRepository;

    @Override
    public Card pay(CardPaymentRequestDTO cardPaymentRequestDTO) {
        Card card = cardRepository.getCardByCardNo(cardPaymentRequestDTO.cardNo());
        Account account = card.getAccount();

        log.info(account.getBalance().toString() + " " + cardPaymentRequestDTO.amount().toString());
        log.info(String.valueOf((account.getBalance().compareTo(cardPaymentRequestDTO.amount()) > -1)));

        if (account.getBalance().compareTo(cardPaymentRequestDTO.amount()) > -1) {

            if (card.getCardType().equals(CardType.CREDIT_CARD)) {
                if (cardPaymentRequestDTO.amount().compareTo(card.getCardDebt()) > 0) {
                    throw new TransferOperationException.PaymentCanNotProceedException("Your sending amount has to be equal or less than your debt ");
                }
                card.setCardDebt(card.getCardDebt().subtract(cardPaymentRequestDTO.amount()));
                card.getAccount().setBalance(card.getAccount().getBalance().subtract(cardPaymentRequestDTO.amount()));
                cardRepository.save(card);
            }
            if (card.getCardType().equals(CardType.BANK_CARD)) {
                card.setCardBalance(card.getCardBalance().add(cardPaymentRequestDTO.amount()));
                card.getAccount().setBalance(card.getAccount().getBalance().subtract(cardPaymentRequestDTO.amount()));
                cardRepository.save(card);

            }
            return card;
        }
        throw new TransferOperationException.PaymentCanNotProceedException("You do not have enough money on your account");
    }
}
