package com.example.bankingsystem.model.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public record CustomerUpdateRequestDTO(@Column(nullable = false)String customerName,
                                       @Column(nullable = false)String customerSurname,
                                       @Email @Column(nullable = false)String customerEmail,
                                       String customerPhone,
                                       @Min(value = 1,message = "Customer ID must be bigger than 0") Long id) {
}