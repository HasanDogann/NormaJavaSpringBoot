package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.abstracts.CustomerConverter;
import com.example.bankingsystem.core.utilities.Result;
import com.example.bankingsystem.core.utilities.SuccessDataResult;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;



    @PostMapping
    public Result addCustomer(@RequestBody CustomerCreateRequestDTO customerCreateRequestDTO){
        customerService.addCustomer(customerCreateRequestDTO);
        return new Result(true,"Customer is created successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
       return ResponseEntity.ok().body(customer);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        Collection<CustomerGetResponseDTO> customers= customerService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id,
                                            @RequestParam(name = "hardDelete", required = false) boolean isHardDelete){
        customerService.deleteCustomer(id,isHardDelete);
        return ResponseEntity.ok().body("Customer is deleted successfully");
    }

}
