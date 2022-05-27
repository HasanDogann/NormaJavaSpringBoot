package com.example.bankingsystem.service.payment.strategies;

import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CardRepository;
import com.example.bankingsystem.service.payment.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 * @Date 24.05.2022
 */
@Component
@RequiredArgsConstructor
public class CreditCardPaymentByATM implements PaymentStrategy {

    private final CardRepository cardRepository;


    @Override
    public Card pay(CardPaymentRequestDTO cardPaymentRequestDTO) {

        Card card = cardRepository.getCardByCardNo(cardPaymentRequestDTO.cardNo());

        if (card.getCardType().equals(CardType.CREDIT_CARD)) {
            //Check if sending amount bigger than debt
            if (cardPaymentRequestDTO.amount().compareTo(card.getCardDebt()) > 0) {
                throw new TransferOperationException.PaymentCanNotProceedException("Your sending amount has to be equal or less than your debt ");
            }
            card.setCardDebt(card.getCardDebt().subtract(cardPaymentRequestDTO.amount()));
            cardRepository.save(card);
        }
        if (card.getCardType().equals(CardType.BANK_CARD)) {
            card.setCardBalance(card.getCardBalance().add(cardPaymentRequestDTO.amount()));
            cardRepository.save(card);
        }

        return card;

    }
}
