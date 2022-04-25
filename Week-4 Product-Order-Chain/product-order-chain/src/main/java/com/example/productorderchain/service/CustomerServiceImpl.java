package com.example.productorderchain.service;

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
    public void create(CreateCustomerRequestDTO createCustomerRequestDTO) {
        Customer customer = customerConverter.toCustomer(createCustomerRequestDTO);
        customerRepository.save(customer);
    }

    @Override
    public CreateCustomerRequestDTO getCustomer(Long id) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        return customerConverter.toCreateCustomerRequest(customer);
    }

    @Override
    public Collection<GetCustomersResponseDTO> getCustomers() {
        return customerRepository
                .findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customerConverter::toGetCustomersResponse)
                .toList();
    }

    @Override
    public void delete(Long id, boolean hardDelete) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        if (customer.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted");
        }
        if (hardDelete) {
            customerRepository.delete(customer);
            return;
        }
        customer.setDeleted(true);
        customerRepository.save(customer);
    }
}
