package com.example.bankingsystem.service;

import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;

import java.util.Collection;

public interface CustomerService {

    void addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    Customer getCustomer(Long id);

    Collection<CustomerGetResponseDTO> getAllCustomers();

    String deleteCustomer(Long id,boolean isHardDelete);

    void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}
