package com.example.bankingsystem.core.payment.strategies;

import com.example.bankingsystem.core.payment.PaymentStrategy;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
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
