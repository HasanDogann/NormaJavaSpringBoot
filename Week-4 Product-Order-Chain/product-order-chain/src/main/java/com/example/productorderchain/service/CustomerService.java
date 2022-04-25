package com.example.productorderchain.service;

import com.example.productorderchain.dto.process.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.GetCustomersResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface CustomerService {
    void create(CreateCustomerRequestDTO customerDTO);

    CreateCustomerRequestDTO getCustomer(Long id) throws BaseException;

    Collection<GetCustomersResponseDTO> getCustomers();

    void delete(Long id, boolean hardDelete) throws BaseException;
}
