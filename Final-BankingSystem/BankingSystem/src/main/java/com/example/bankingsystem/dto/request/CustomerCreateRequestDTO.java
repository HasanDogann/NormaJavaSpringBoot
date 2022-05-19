package com.example.bankingsystem.dto.request;

import com.example.bankingsystem.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.dto.model.CustomerAddressDTO;

public record CustomerCreateRequestDTO(String customerName,
                                       String customerSurname,
                                       String customerEmail,
                                       String customerPhone,
                                       String password,
                                       AccountOptionsDTO accountOptionsDTO,
                                       CustomerAddressDTO customerAddress
                                       ) {
}
