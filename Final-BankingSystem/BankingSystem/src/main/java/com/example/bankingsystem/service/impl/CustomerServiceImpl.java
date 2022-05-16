package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.abstracts.CustomerConverter;
import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Override
    public Result addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
       Customer customer = customerConverter.toCustomer(customerCreateRequestDTO);
       customerRepository.save(customer);
       return new Result(true,"Customer added successfully.");
    }



    @Override
    public Customer getCustomer(Long id){
       Customer customer =  customerRepository
                .findById(id)
                .orElseThrow(()->
                new ServiceOperationNotFoundException.CustomerNotFoundException("Customer can not found!"));
       if(customer.isDeleted()){
           throw new ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException("Customer is already deleted");
       }
      return customer;
    }

    @Override
    public Collection<CustomerGetResponseDTO> getAllCustomers() {
     return  customerRepository.findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customerConverter::toCustomerResponse).toList();

    }



    @Override
    public String deleteCustomer(Long id,boolean hardDelete){
        Customer customer = customerRepository.getById(id);

        if (customer.isDeleted()){
            throw  new ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException("Customer is already deleted");
        }
        if(hardDelete){
            customerRepository.removeCustomerById(id);
        return "Customer is deleted successfully";
        }

           customer.setDeleted(true);
           customerRepository.save(customer);
           return "Customer is deleted successfully";

    }




}
