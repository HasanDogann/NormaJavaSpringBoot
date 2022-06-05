package com.example.bankingsystem.service.transfer.strategies;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.model.entity.enums.TransferType;
import com.example.bankingsystem.model.entity.exchange.ExchangeModel;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CardService;
import com.example.bankingsystem.service.transfer.TransferStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 25.05.2022
 */
@Component
@RequiredArgsConstructor
public class MoneyTransferByPurchase implements TransferStrategy {

    private final AccountService accountService;
    private final RestTemplate restTemplate;
    private final CardService cardService;
    final HttpHeaders headers = new HttpHeaders();

    @Override
    @Transactional
    public Transaction pay(TransactionRequestDTO transactionRequestDTO) {


        Transaction transaction = new Transaction();
        Card senderCard = cardService.getCardBalance(transactionRequestDTO.senderIban());
        Account receiverAccount = accountService.getAccountByIBAN(transactionRequestDTO.receiverIban());


        //IF CUSTOMER's CREDIT CARD CURRENCY TYPE IS DIFFERENT FROM TRY
        if (!senderCard.getAccount().getBalanceCurrencyType().equals(BalanceCurrencyType.TRY)) {

            return purchaseByForeignCard(transactionRequestDTO, senderCard, receiverAccount, transaction);
        }
        //IF CUSTOMER's CREDIT CARD CURRENCY TYPE IS TRY
        return purchaseByCard(transactionRequestDTO, senderCard, receiverAccount, transaction);
    }


    private double moneyExchange(TransactionRequestDTO transactionRequestDTO, Card senderCard) {

        String apiKey = "eWFAkbyzvFjyBWQ8rcggHOsChPhOxyfX";
        headers.add("apikey", apiKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        String url = "https://api.apilayer.com/exchangerates_data/convert?to=TRY&from=" + senderCard.getAccount().getBalanceCurrencyType() + "&amount=" + transactionRequestDTO.amount() + "";
        ResponseEntity<ExchangeModel> model = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ExchangeModel.class);

        return (Objects.requireNonNull(model.getBody()).result);
    }

    private Transaction purchaseByForeignCard(TransactionRequestDTO transactionRequestDTO, Card senderCard, Account receiverAccount, Transaction transaction) {
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.PURCHASE);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());
        //IF CARD BALANCE IS ENOUGH CHECK FOR PURCHASE BY BANK CARD
        if (senderCard.getCardType().equals(CardType.BANK_CARD)) {
            if (senderCard.getCardBalance().compareTo(transactionRequestDTO.amount()) > -1) {
                double exchangeAmount = moneyExchange(transactionRequestDTO, senderCard);
                transaction.setTransferAmount(BigDecimal.valueOf(exchangeAmount));
                senderCard.setCardBalance(senderCard.getCardBalance().subtract(transactionRequestDTO.amount()));
                receiverAccount.setBalance(receiverAccount.getBalance().add(BigDecimal.valueOf(exchangeAmount)));
                return transaction;
            } else
                throw new TransferOperationException.TransferCanNotProceedException("You do not have enough limit for this purchase!");
        }
        //IF CARD BALANCE IS ENOUGH CHECK FOR PURCHASE BY CREDIT CARD
        if (senderCard.getCardBalance().compareTo(transactionRequestDTO.amount()) > -1) {
            double exchangeAmount = moneyExchange(transactionRequestDTO, senderCard);
            transaction.setTransferAmount(BigDecimal.valueOf(exchangeAmount));
            senderCard.setCardBalance(senderCard.getCardBalance().subtract(transactionRequestDTO.amount()));
            receiverAccount.setBalance(receiverAccount.getBalance().add(BigDecimal.valueOf(exchangeAmount)));
            return transaction;
        }
        boolean isLimitEnough = (senderCard.getCardLimit().compareTo(transactionRequestDTO.amount().add(senderCard.getCardDebt()).subtract(senderCard.getCardBalance())))>-1;
        if (!isLimitEnough) {
            throw new TransferOperationException.TransferCanNotProceedException("You do not have enough limit for this purchase.");
        }
        double exchangeAmount = moneyExchange(transactionRequestDTO, senderCard);
        senderCard.setCardDebt(senderCard.getCardDebt().add(transactionRequestDTO.amount()).subtract(senderCard.getCardBalance()));
        senderCard.setCardBalance(BigDecimal.ZERO);
        transaction.setTransferAmount(BigDecimal.valueOf(exchangeAmount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(BigDecimal.valueOf(exchangeAmount)));
        return transaction;

    }

    private Transaction purchaseByCard(TransactionRequestDTO transactionRequestDTO, Card senderCard, Account receiverAccount, Transaction transaction) {
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.PURCHASE);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());

        //IF CARD BALANCE IS ENOUGH CHECK FOR PURCHASE BY BANK CARD
        if (senderCard.getCardType().equals(CardType.BANK_CARD)) {

            if (senderCard.getCardBalance().compareTo(transactionRequestDTO.amount()) > -1) {
                transaction.setTransferAmount(transactionRequestDTO.amount());
                senderCard.setCardBalance(senderCard.getCardBalance().subtract(transactionRequestDTO.amount()));
                receiverAccount.setBalance(receiverAccount.getBalance().add(transactionRequestDTO.amount()));
                return transaction;
            } else
                throw new TransferOperationException.TransferCanNotProceedException("You do not have enough limit for this purchase");
        }


        //IF CARD BALANCE IS ENOUGH CHECK FOR PURCHASE BY CREDIT CARD

        if (senderCard.getCardBalance().compareTo(transactionRequestDTO.amount()) > -1) {
            transaction.setTransferAmount(transactionRequestDTO.amount());
            senderCard.setCardBalance(senderCard.getCardBalance().subtract(transactionRequestDTO.amount()));
            receiverAccount.setBalance(receiverAccount.getBalance().add(transactionRequestDTO.amount()));
            return transaction;
        }
        boolean isLimitEnough = (senderCard.getCardLimit().compareTo(transactionRequestDTO.amount().add(senderCard.getCardDebt()).subtract(senderCard.getCardBalance())))>-1;
        if (!isLimitEnough) {
            throw new TransferOperationException.TransferCanNotProceedException("You do not have enough limit for this purchase");
        }

        transaction.setTransferAmount(transactionRequestDTO.amount());
        senderCard.setCardDebt(senderCard.getCardDebt().add(transactionRequestDTO.amount()).subtract(senderCard.getCardBalance()));
        senderCard.setCardBalance(BigDecimal.ZERO);
        receiverAccount.setBalance(receiverAccount.getBalance().add(transactionRequestDTO.amount()));
        return transaction;
    }
}
