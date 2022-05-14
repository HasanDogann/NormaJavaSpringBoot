package com.example.bankingsystem.service.concretes;

import com.example.bankingsystem.converter.abstracts.CustomerConverter;
import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;

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

      return customerRepository.getById(id);
    }

    @Override
    public Collection<CustomerGetResponseDTO> getAllCustomers() {
     return  customerRepository.findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customerConverter::toCustomerResponse).toList();

    }


}
