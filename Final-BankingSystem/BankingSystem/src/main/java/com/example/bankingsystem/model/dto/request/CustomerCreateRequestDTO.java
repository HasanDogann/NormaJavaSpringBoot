package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.model.dto.model.CustomerAddressDTO;

public record CustomerCreateRequestDTO(String customerName,
                                       String customerSurname,
                                       String customerEmail,
                                       String customerPhone,
                                       AccountOptionsDTO accountOptionsDTO,
                                       CustomerAddressDTO customerAddress
                                       ) {
}
