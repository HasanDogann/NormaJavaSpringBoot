package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.TransferType;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 5.06.2022
 */
public record PurchaseReceiptCreateRequestDTO(TransferType transferType,
                                              @Pattern(regexp = "[2][0][2][0-9]-[0-1][0-9]-[0-3][0-9]",message = "Format: 202Y-MM-DD Your purchaseDate input should match with date format.") String purchaseDate,
                                              @Pattern(regexp = "[1-9]\\d{15}") String senderCardNo) {
}
