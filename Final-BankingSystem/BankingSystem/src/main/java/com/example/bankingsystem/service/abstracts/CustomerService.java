package com.example.bankingsystem.service.abstracts;

import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;

import java.util.Collection;

public interface CustomerService {

    Result addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    Customer getCustomer(Long id);

    Collection<CustomerGetResponseDTO> getAllCustomers();
}
