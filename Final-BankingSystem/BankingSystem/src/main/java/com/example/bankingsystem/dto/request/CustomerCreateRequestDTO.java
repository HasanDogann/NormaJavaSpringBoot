package com.example.bankingsystem.dto.request;

import com.example.bankingsystem.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.entity.CustomerAddress;

public record CustomerCreateRequestDTO(String customerName,
                                       String customerSurname,
                                       String customerEmail,
                                       String customerPhone,
                                       CustomerAddressDTO customerAddress
                                       ) {
}
