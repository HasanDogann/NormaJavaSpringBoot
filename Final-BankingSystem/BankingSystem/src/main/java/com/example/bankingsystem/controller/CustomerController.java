package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.CustomerFacade;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */
@RestController
@RequestMapping(value = "api/v2/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerFacade customerFacade;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        logger.trace("Post method used for adding customer ");
        return customerFacade.addCustomer(customerCreateRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerGetResponseDTO> getCustomer(@Valid @Min(1) @PathVariable Long id) {
        logger.trace("Get method used for getting Customer : {}", id);
        return customerFacade.getCustomer(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/getCustomerByMail/{mail}")
    public ResponseEntity<CustomerGetResponseDTO> getCustomerByMail(@Valid @Email(message = "Email doesn't have a valid format") @PathVariable String mail) {
        logger.trace("Get method used for getting Customer with Mail: {}", mail);
        return customerFacade.getCustomerByEmail(mail);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<CustomerGetResponseDTO>> getAllCustomers() {
        logger.trace("Get method used for getting all Customers");
        return customerFacade.getAllCustomers();
    }

    @PreAuthorize(value = "hasAnyAuthority('USER','ADMIN')")
    @PatchMapping(path = "/update")
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        logger.trace("Patch method used for updating Customer");
        return customerFacade.updateCustomer(customerUpdateRequestDTO);

    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@Valid @Min(1) @PathVariable Long id,
                                                 @RequestParam(name = "hardDelete", required = false) boolean isHardDelete) {
        logger.trace("Delete method used for deleting Customer : {}", id);
        return customerFacade.deleteCustomer(id, isHardDelete);
    }

}
