package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final AccountService accountService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter, @Lazy AccountService accountService) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
        this.accountService = accountService;
    }

    @Override
    public void addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) throws ConstraintViolationException {
        Customer c = customerRepository.findCustomerByEMailAddress(customerCreateRequestDTO.customerEmail());
        if (Objects.isNull(c)) {
            Customer customer = customerConverter.toCustomer(customerCreateRequestDTO);
            customerRepository.save(customer);
        } else {
            throw new ServiceOperationCanNotCreateException.CustomerIsAlreadyCreatedException("A customer is already using this e mail");
        }
    }


    @Override
    public Customer getCustomer(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ServiceOperationNotFoundException.CustomerNotFoundException("Customer can not found!"));
        if (customer.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException("Customer is already deleted");
        }
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = customerRepository
                .findCustomerByEMailAddress(email);
        if (Objects.isNull(customer)) {
            return null;
        }
        return customer;
    }

    @Override
    public Collection<CustomerGetResponseDTO> getAllCustomers() {
        return customerRepository.findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customerConverter::toCustomerResponse)
                .toList();

    }


    @Override
    public String deleteCustomer(Long id, boolean hardDelete) {
        Customer customer = customerRepository.getById(id);

        Collection<Account> accountCollection = accountService.getAllAccountOneCustomer(id).stream().filter(i -> (i.getBalance().compareTo(BigDecimal.ZERO) > 0)).toList();

        if (accountCollection.isEmpty()) {

            if (customer.isDeleted()) {
                throw new ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException("Customer is already deleted");
            }
            if (hardDelete) {
                customerRepository.removeCustomerById(id);
                return "Customer is deleted successfully";
            }

            customer.setDeleted(true);
            customerRepository.save(customer);
            return "Customer is deleted successfully";
        }
        throw new ServiceOperationCanNotDeleteException.CustomerBalanceNotZero("Customer has account that balance of it has money");
    }

    @Override
    public void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = customerConverter.toCustomerFromUpdateRequest(customerUpdateRequestDTO);
        customerRepository.save(customer);

    }



}
