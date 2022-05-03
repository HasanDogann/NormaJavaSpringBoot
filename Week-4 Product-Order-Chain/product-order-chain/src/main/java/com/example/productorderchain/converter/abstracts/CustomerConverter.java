package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.get.GetCustomersResponseDTO;
import com.example.productorderchain.model.Customer;

public interface CustomerConverter {

    Customer toCustomer(CreateCustomerRequestDTO createCustomerRequestDTO);

    CreateCustomerRequestDTO toCreateCustomerRequest(Customer customer);

    GetCustomersResponseDTO toGetCustomersResponse(Customer customer);
}
