package com.example.bankingsystem.service.transfer.strategies;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.service.transfer.TransferStrategy;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.TransferType;
import com.example.bankingsystem.model.entity.exchange.ExchangeModel;
import com.example.bankingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MoneyTransferByIban implements TransferStrategy {

    private final AccountService accountService;
    private final RestTemplate restTemplate;
    private String url;
    private static final String API_KEY = "eWFAkbyzvFjyBWQ8rcggHOsChPhOxyfX";
    final HttpHeaders headers = new HttpHeaders();

    @Override
    @Transactional
    public Transaction pay(TransactionRequestDTO transactionRequestDTO) {

        Account sender = accountService.getAccount(transactionRequestDTO.senderIban());
        Account receiver = accountService.getAccount(transactionRequestDTO.receiverIban());

        if (sender.getBalance().compareTo(transactionRequestDTO.amount()) < 0) {
            throw new TransferOperationException.TransferCanNotProceedException("Sender account doesn't have enough money to send this amount!");
        }
        else if (sender.getAccountType().equals(AccountType.DEPOSIT_ACCOUNT) ) {
            throw new TransferOperationException.TransferCanNotProceedException("Sender account type can not be Deposit Account");
        }

        //IF SENDER  CURRENCY TYPE FOREIGN CURRENCY AND RECEIVER CURRENCY IS TRY.
        if (!sender.getBalanceCurrencyType().equals(BalanceCurrencyType.TRY) && receiver.getBalanceCurrencyType().equals(BalanceCurrencyType.TRY)) {

            return sendingMoneyFromForeignToTRYAccount(transactionRequestDTO);
        }

        //IF RECEIVER CURRENCY TYPE DIFFERENT FROM TL AND SENDER CURRENCY IS TL.
        if (sender.getBalanceCurrencyType().equals(BalanceCurrencyType.TRY) && !receiver.getBalanceCurrencyType().equals(BalanceCurrencyType.TRY)) {

            return sendingMoneyFromTRYtoForeignAccount(transactionRequestDTO);
        }

        return sendingMoneyBothTRYAccount(transactionRequestDTO);
    }

    private Transaction sendingMoneyFromForeignToTRYAccount(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = new Transaction();
        Account sender = accountService.getAccount(transactionRequestDTO.senderIban());
        Account receiver = accountService.getAccount(transactionRequestDTO.receiverIban());

        headers.add("apikey", API_KEY);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        url = "https://api.apilayer.com/exchangerates_data/convert?to=TL&from=" + sender.getBalanceCurrencyType() + "&amount=" + transactionRequestDTO.amount() + "";

        ResponseEntity<ExchangeModel> model = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ExchangeModel.class);

        double exchangeAmount = (Objects.requireNonNull(model.getBody()).result);

        transaction.setTransferAmount(BigDecimal.valueOf(exchangeAmount));
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.IBAN);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());
        //EURO or DOLLAR is sent to TRY
        sender.setBalance(sender.getBalance().subtract(transactionRequestDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(BigDecimal.valueOf(exchangeAmount)));
        return transaction;

    }

    private Transaction sendingMoneyFromTRYtoForeignAccount(TransactionRequestDTO transactionRequestDTO) {

        Transaction transaction = new Transaction();
        Account sender = accountService.getAccount(transactionRequestDTO.senderIban());
        Account receiver = accountService.getAccount(transactionRequestDTO.receiverIban());

        headers.add("apikey", API_KEY);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        url = "https://api.apilayer.com/exchangerates_data/convert?to=" + receiver.getBalanceCurrencyType() + "&from=TRY&amount=" + transactionRequestDTO.amount() + "";
        ResponseEntity<ExchangeModel> model = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ExchangeModel.class);

        double exchangeAmount = (Objects.requireNonNull(model.getBody()).result);

        transaction.setTransferAmount(BigDecimal.valueOf(exchangeAmount));
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.IBAN);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());
        //TRY IS CONVERTED TO EUR OR USD
        sender.setBalance(sender.getBalance().subtract(transactionRequestDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(BigDecimal.valueOf(exchangeAmount)));
        return transaction;
    }

    private Transaction sendingMoneyBothTRYAccount(TransactionRequestDTO transactionRequestDTO){


        Transaction transaction = new Transaction();
        Account sender = accountService.getAccount(transactionRequestDTO.senderIban());
        Account receiver = accountService.getAccount(transactionRequestDTO.receiverIban());

        transaction.setTransferAmount(transactionRequestDTO.amount());
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.IBAN);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());
        sender.setBalance(sender.getBalance().subtract(transactionRequestDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionRequestDTO.amount()));

        return transaction;
    }

}
