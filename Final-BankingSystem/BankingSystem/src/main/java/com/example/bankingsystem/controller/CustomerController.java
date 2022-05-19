package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "api/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;



    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerCreateRequestDTO customerCreateRequestDTO){
        customerService.addCustomer(customerCreateRequestDTO);
        return ResponseEntity.ok().body("Customer is created successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        CustomerGetResponseDTO customerGetResponseDTO = customerConverter.toCustomerResponse(customer);
       return ResponseEntity.ok().body(customerGetResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        Collection<CustomerGetResponseDTO> customers= customerService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO){
        customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok().body("Customer is updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id,
                                            @RequestParam(name = "hardDelete", required = false) boolean isHardDelete){
        customerService.deleteCustomer(id,isHardDelete);
        return ResponseEntity.ok().body("Customer is deleted successfully");
    }

}
