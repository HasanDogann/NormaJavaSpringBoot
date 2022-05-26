package com.example.bankingsystem.facade.impl;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.facade.CustomerFacade;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Override
    public ResponseEntity<String> addCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        customerService.addCustomer(customerCreateRequestDTO);
        return ResponseEntity.ok().body("Customer is added successfully");
    }

    @Override
    public ResponseEntity<CustomerGetResponseDTO> getCustomer(Long id) {

        CustomerGetResponseDTO customerGetResponseDTO = customerConverter
                .toCustomerResponse(customerService.getCustomer(id));
        return ResponseEntity.ok().body(customerGetResponseDTO);
    }

    @Override
    public ResponseEntity<CustomerGetResponseDTO> getCustomerByEmail(String email) {

        CustomerGetResponseDTO customerGetResponseDTO = customerConverter
                .toCustomerResponse(customerService.getCustomerByEmail(email));
        if(Objects.isNull(customerGetResponseDTO)){
            ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok().body(customerGetResponseDTO);
    }

    @Override
    public ResponseEntity<Collection<CustomerGetResponseDTO>> getAllCustomers() {
        Collection<CustomerGetResponseDTO> collection = customerService.getAllCustomers()
                .stream()
                .map(customerConverter::toCustomerResponse).toList();
        if (collection.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(collection);
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Long id, boolean isHardDelete) {
        return ResponseEntity.ok().body(customerService.deleteCustomer(id,isHardDelete));

    }

    @Override
    public ResponseEntity<String> updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok().body("Customer is updated.");
    }
}
