package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.abstracts.CustomerConverter;
import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.core.utilities.SuccessDataResult;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;



    @PostMapping
    public ResponseEntity<Result> addCustomer(@RequestBody CustomerCreateRequestDTO customerCreateRequestDTO){
        customerService.addCustomer(customerCreateRequestDTO);
        return ResponseEntity.ok().body(new Result(true,"Customer is created successfully"));
    }


    @GetMapping("/getCustomer")
    public ResponseEntity<SuccessDataResult<CustomerGetResponseDTO>> getCustomer(@RequestParam(value = "customerId") Long id){
        Customer customer = customerService.getCustomer(id);
       return ResponseEntity.ok().body(new SuccessDataResult<>(customerConverter.toCustomerResponse(customer),"Customer is listed successfully"));
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<CustomerGetResponseDTO>>> getAllCustomers(){

        return  ResponseEntity.ok().body(new SuccessDataResult<>(customerService.getAllCustomers(),"Customers are listed successfully"));
    }
}
