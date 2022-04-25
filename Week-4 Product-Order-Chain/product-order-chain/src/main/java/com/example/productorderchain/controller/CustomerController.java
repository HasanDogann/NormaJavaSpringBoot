package com.example.productorderchain.controller;

import com.example.productorderchain.dto.process.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.GetCustomersResponseDTO;
import com.example.productorderchain.service.CustomerService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Qualifier("customerReqQ")
    private final Validator<CreateCustomerRequestDTO> createCustomerValidator;
    @Qualifier("customerIDQ")
    private final Validator<Long> customerIdValidator;

    private final CustomerService customerService;


    public CustomerController(Validator<CreateCustomerRequestDTO> createCustomerValidator, @Qualifier("customerIDQ") Validator<Long> customerIdValidator, CustomerService customerService){
        this.createCustomerValidator = createCustomerValidator;
        this.customerIdValidator = customerIdValidator;
        this.customerService=customerService;
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {
        createCustomerValidator.validate(createCustomerRequestDTO);
        customerService.create(createCustomerRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<GetCustomersResponseDTO>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerRequestDTO> getCustomer(@PathVariable Long id) {
        customerIdValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        customerIdValidator.validate(id);
        customerService.delete(id,hardDelete);
        return ResponseEntity.ok().build();
    }


}
