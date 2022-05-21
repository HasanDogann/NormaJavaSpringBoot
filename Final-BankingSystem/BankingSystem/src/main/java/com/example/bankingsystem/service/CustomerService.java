package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.model.entity.Customer;

import java.util.Collection;

public interface CustomerService {

    void addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);
 //   void addCustomer(UserRegisterRequest userRegisterRequest);

    Customer getCustomer(Long id);

    Customer getCustomerByEmail(String email);

    Collection<CustomerGetResponseDTO> getAllCustomers();

    String deleteCustomer(Long id,boolean isHardDelete);

    void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

}
