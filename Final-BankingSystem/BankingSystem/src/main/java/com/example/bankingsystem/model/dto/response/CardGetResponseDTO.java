package com.example.bankingsystem.model.dto.response;

import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 23.05.2022
 */
public record CardGetResponseDTO(String cardNo,
                                 BigDecimal cardDebt,
                                 BigDecimal cardBalance,
                                 String customerName,
                                 String customerSurname,
                                 Long customerID,
                                 Long accountID,
                                 Long cardID) {
}
