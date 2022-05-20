package com.example.bankingsystem.converter;

import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.dto.request.UserRegisterRequest;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;

public interface CustomerConverter {
    Customer toCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    Customer toCustomer(UserRegisterRequest userRegisterRequest);

    CustomerGetResponseDTO toCustomerResponse(Customer customer);

    Customer toCustomerFromUpdateRequest(CustomerUpdateRequestDTO customerUpdateRequestDTO);


}
