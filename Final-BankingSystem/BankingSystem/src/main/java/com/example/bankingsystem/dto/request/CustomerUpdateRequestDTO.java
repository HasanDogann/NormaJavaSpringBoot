package com.example.bankingsystem.dto.request;

public record CustomerUpdateRequestDTO(String customerName,
                                       String customerSurname,
                                       String customerEmail,
                                       String customerPhone,
                                       Long id) {
}
