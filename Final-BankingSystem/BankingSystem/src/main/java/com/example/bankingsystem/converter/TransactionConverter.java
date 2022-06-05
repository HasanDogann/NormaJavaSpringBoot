package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.PurchaseReceiptCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.dto.response.PurchaseReceiptGetResponseDTO;
import com.example.bankingsystem.model.dto.response.TransactionGetResponseDTO;
import com.example.bankingsystem.model.entity.Transaction;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public interface TransactionConverter {


 TransactionGetResponseDTO toTransactionResponse(Transaction transaction);

 PurchaseReceiptGetResponseDTO toPurchaseResponse(Transaction transaction);

}
