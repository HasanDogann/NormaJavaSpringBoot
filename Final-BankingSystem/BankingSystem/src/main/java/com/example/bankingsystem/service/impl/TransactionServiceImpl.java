package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.core.transfer.TransferStrategy;
import com.example.bankingsystem.core.transfer.strategies.MoneyTransferByIban;
import com.example.bankingsystem.core.transfer.strategies.MoneyTransferByPurchase;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.TransferType;
import com.example.bankingsystem.repository.TransactionRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 24.05.2022
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;
    private TransferStrategy transferStrategy;
    private final AccountService accountService;

    @Override
    public void moneyTransfer(TransactionRequestDTO transactionRequestDTO) {


        TransferType transferType=transactionRequestDTO.transferType();

        if (transferType.equals(TransferType.IBAN)) {
            transferStrategy = new MoneyTransferByIban(accountService,restTemplate);
        } else if (transferType.equals(TransferType.PURCHASE)) {
            transferStrategy = new MoneyTransferByPurchase();
        }

        Transaction transaction =transferStrategy.pay(transactionRequestDTO);
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


}
