package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CustomerFacade {

    ResponseEntity<String> addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);

    ResponseEntity<CustomerGetResponseDTO> getCustomer(Long id);

    ResponseEntity<CustomerGetResponseDTO> getCustomerByEmail(String email);

    ResponseEntity<Collection<CustomerGetResponseDTO>> getAllCustomers();

    ResponseEntity<String> deleteCustomer(Long id, boolean isHardDelete);

    ResponseEntity<String> updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}
