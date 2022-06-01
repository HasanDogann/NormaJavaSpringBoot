
package com.example.bankingsystem.controller;



import com.example.bankingsystem.facade.TransactionFacade;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;

import com.example.bankingsystem.service.TransactionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


/**

 * @author Hasan DOĞAN

 * @Project IntelliJ IDEA

 * @Date 25.05.2022

 */

@RestController
@RequestMapping("/api/v2/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFacade transactionFacade;
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @PostMapping
    public ResponseEntity<?> sendMoney(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.sendMoney(transactionRequestDTO);
    }
    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@Valid @Min(1)@PathVariable Long id){

        return transactionFacade.getTransaction(id);
    }


}



