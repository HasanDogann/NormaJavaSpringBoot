package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.CustomerFacade;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@RequestMapping(value = "api/v2/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        return customerFacade.addCustomer(customerCreateRequestDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@Valid @Min(1) @PathVariable Long id) {
        return ResponseEntity.ok().body(customerFacade.getCustomer(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return customerFacade.getAllCustomers();
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        return customerFacade.updateCustomer(customerUpdateRequestDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@Valid @Min(1) @PathVariable Long id,
                                            @RequestParam(name = "hardDelete", required = false) boolean isHardDelete) {
        return customerFacade.deleteCustomer(id, isHardDelete);
    }

}
