package com.example.bankingsystem.service.payment.strategies;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CardRepository;
import com.example.bankingsystem.service.payment.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.bankingsystem.core.constants.ConstantUtils.DAILY_MAX_LIMIT_OF_ATM;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 * @Date 24.05.2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CreditCardPaymentByATM implements PaymentStrategy {

    private final CardRepository cardRepository;


    @Override
    public Card pay(CardPaymentRequestDTO cardPaymentRequestDTO) {

        Card card = cardRepository.getCardByCardNo(cardPaymentRequestDTO.cardNo());

        if (card.getCardType().equals(CardType.CREDIT_CARD)) {


            //Check if sending amount bigger than debt
            log.info(card.getCardDebt().toString()+" card debt");
            log.info(cardPaymentRequestDTO.amount().toString()+" amount price");

            if (cardPaymentRequestDTO.amount().compareTo(card.getCardDebt()) > 0) {
                throw new TransferOperationException.PaymentCanNotProceedException("Your sending amount has to be equal or less than your debt ");
            }
            card.setCardDebt(card.getCardDebt().subtract(cardPaymentRequestDTO.amount()));
            cardRepository.save(card);
        }
        if (card.getCardType().equals(CardType.BANK_CARD)) {
            if(cardPaymentRequestDTO.amount().compareTo(ConstantUtils.DAILY_MAX_LIMIT_OF_ATM)>0){
                throw new  TransferOperationException.PaymentCanNotProceedException("You can send to Card max 5000 in a day!");
            }
            card.setCardBalance(card.getCardBalance().add(cardPaymentRequestDTO.amount()));
            cardRepository.save(card);
        }

        return card;

    }
}
