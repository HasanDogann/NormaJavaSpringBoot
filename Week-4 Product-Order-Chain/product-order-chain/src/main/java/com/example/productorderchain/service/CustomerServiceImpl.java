package com.example.productorderchain.service;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.productorderchain.converter.CustomerConverter;
import com.example.productorderchain.dto.process.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.GetCustomersResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Customer;
import com.example.productorderchain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;

    @Override
    public Result create(CreateCustomerRequestDTO createCustomerRequestDTO) {
        Customer customer = customerConverter.toCustomer(createCustomerRequestDTO);
        customerRepository.save(customer);
        return new SuccessResult("Customer "+customer.getUsername()+" is added successfully.");
    }

    @Override
    public SuccessDataResult<CreateCustomerRequestDTO> getCustomer(Long id) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        if (customer.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer was deleted");
        }
        return new SuccessDataResult<>(customerConverter.toCreateCustomerRequest(customer),"Customer is listed successfully");
    }

    @Override
    public SuccessDataResult<Collection<GetCustomersResponseDTO>> getCustomers() {
        return new SuccessDataResult<>(customerRepository
                .findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customerConverter::toGetCustomersResponse)
                .toList(),"Customers are listed successfully");
    }

    @Override
    public Result delete(Long id, boolean hardDelete) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        if (customer.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted");
        }
        if (hardDelete) {
            customerRepository.delete(customer);
            return new SuccessResult("Customer "+customer.getUsername()+" is deleted with HardDelete successfully");
        }
        customer.setDeleted(true);
        customerRepository.save(customer);
        return new SuccessResult("Customer "+customer.getUsername()+" is deleted with SoftDelete successfully");
    }
}
