package com.example.bankingsystem.dto.request;

import com.example.bankingsystem.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.entity.CustomerAddress;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;

public record CustomerCreateRequestDTO(String customerName,
                                       String customerSurname,
                                       String customerEmail,
                                       String customerPhone,
                                       AccountOptionsDTO accountOptionsDTO,
                                       CustomerAddressDTO customerAddress
                                       ) {
}
