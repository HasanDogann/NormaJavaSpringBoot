package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.model.entity.Customer;

public interface CustomerConverter {
    Customer toCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    CustomerGetResponseDTO toCustomerResponse(Customer customer);

    Customer toCustomerFromUpdateRequest(CustomerUpdateRequestDTO customerUpdateRequestDTO);


}
