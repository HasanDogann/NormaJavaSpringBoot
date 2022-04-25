package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.GetCustomersResponseDTO;
import com.example.productorderchain.model.Customer;

public interface CustomerConverter {

    Customer toCustomer(CreateCustomerRequestDTO createCustomerRequestDTO);

    CreateCustomerRequestDTO toCreateCustomerRequest(Customer customer);

    GetCustomersResponseDTO toGetCustomersResponse(Customer customer);
}
