package com.example.bankingsystem.converter.abstracts;

import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;

public interface CustomerConverter {
    Customer toCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    CustomerGetResponseDTO toCustomerResponse(Customer customer);
}
