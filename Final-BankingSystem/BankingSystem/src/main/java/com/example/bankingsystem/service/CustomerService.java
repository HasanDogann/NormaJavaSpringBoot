package com.example.bankingsystem.service;

import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.dto.request.UserRegisterRequest;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;

import java.util.Collection;

public interface CustomerService {

    void addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);
    void addCustomer(UserRegisterRequest userRegisterRequest);

    Customer getCustomer(Long id);

    Customer getCustomerByEmail(String email);

    Collection<CustomerGetResponseDTO> getAllCustomers();

    String deleteCustomer(Long id,boolean isHardDelete);

    void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
    void updateCustomerPassword(Long id ,String password);
}
