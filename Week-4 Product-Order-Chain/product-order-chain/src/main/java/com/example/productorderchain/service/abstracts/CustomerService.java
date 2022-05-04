package com.example.productorderchain.service.abstracts;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.get.GetCustomersResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.model.Customer;

import java.util.Collection;

public interface CustomerService {
    Result create(CreateCustomerRequestDTO customerDTO);

    Customer getCustomer(Long id) throws BaseException;

   SuccessDataResult<Collection<GetCustomersResponseDTO>> getCustomers();

    Result delete(Long id, boolean hardDelete) throws BaseException;
}
