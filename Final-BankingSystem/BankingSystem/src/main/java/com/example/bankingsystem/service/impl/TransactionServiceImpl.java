package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.PurchaseReceiptCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.TransferType;
import com.example.bankingsystem.repository.TransactionRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CardService;
import com.example.bankingsystem.service.TransactionService;
import com.example.bankingsystem.service.transfer.TransferStrategy;
import com.example.bankingsystem.service.transfer.strategies.MoneyTransferByIban;
import com.example.bankingsystem.service.transfer.strategies.MoneyTransferByPurchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Objects;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardService cardService;
    private final RestTemplate restTemplate;
    private TransferStrategy transferStrategy;
    private final AccountService accountService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void moneyTransfer(TransactionRequestDTO transactionRequestDTO) {

        TransferType transferType = transactionRequestDTO.transferType();

        if (transferType.equals(TransferType.IBAN)) {
            transferStrategy = new MoneyTransferByIban(accountService, restTemplate);
        } else if (transferType.equals(TransferType.PURCHASE)) {
            transferStrategy = new MoneyTransferByPurchase(accountService, restTemplate, cardService);
        }

        Transaction transaction = transferStrategy.pay(transactionRequestDTO);
        transactionRepository.save(transaction);

    }

    @Override
    public Transaction getTransaction(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ServiceOperationNotFoundException
                            .TransferNotFoundException("Transaction is not found!");
                });
        if (transaction.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException
                    .TransferAlreadyDeletedException("Transaction was deleted!");
        }
        return transaction;
    }

    @Override
    public Collection<Transaction> getPurchaseReceipts(PurchaseReceiptCreateRequestDTO purchaseDTO) {

        Collection<Transaction> transactions = transactionRepository
                .getTransactionsByTransferTypeIsAndSenderIBANIsAndTransferDateIsGreaterThan(purchaseDTO.transferType(),
                        purchaseDTO.senderCardNo(), purchaseDTO.purchaseDate());
        if (Objects.isNull(transactions)) {
            throw new TransferOperationException.PaymentBillsCanNotProceedException("There is no receipts");
        }
        return transactions;
    }


}
