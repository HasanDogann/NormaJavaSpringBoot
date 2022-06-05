package com.example.bankingsystem.model.dto.response;

import java.math.BigDecimal;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 5.06.2022
 */
public record PurchaseReceiptGetResponseDTO(String cardNo,
                                            String receiptsAfterDate,
                                            BigDecimal purchaseAmount,
                                            String receiverIBAN) {
}
