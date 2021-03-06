package com.example.bankingsystem.model.dto.response;

import java.util.Set;

public record CustomerGetResponseDTO(String customerName,
                                     String customerSurname,
                                     Long customerId,
                                     String customerEmail,
                                     String customerPhone,
                                     Set customerAccounts,
                                     Set customerCards) {
}
