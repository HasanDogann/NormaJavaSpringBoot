package com.example.bankingsystem.core.transfer.strategies;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.core.transfer.TransferStrategy;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Transaction;
import com.example.bankingsystem.model.entity.enums.TransferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 25.05.2022
 */
@Component
@RequiredArgsConstructor
public class MoneyTransferByPurchase implements TransferStrategy {
    @Override
    public Transaction pay(TransactionRequestDTO transactionRequestDTO) {

        Transaction transaction = new Transaction();
        transaction.setTransferAmount(transactionRequestDTO.amount());
        transaction.setSenderIBAN(transactionRequestDTO.senderIban());
        transaction.setReceiverIBAN(transactionRequestDTO.receiverIban());
        transaction.setTransferType(TransferType.PURCHASE);
        transaction.setTransferDate(ConstantUtils.getCurrentDate());


        return transaction;
    }
}
