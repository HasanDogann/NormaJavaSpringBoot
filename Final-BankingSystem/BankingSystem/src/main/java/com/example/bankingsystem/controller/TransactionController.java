
package com.example.bankingsystem.controller;



import com.example.bankingsystem.facade.TransactionFacade;

import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;

import com.example.bankingsystem.service.TransactionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



/**

 * @author Hasan DOĞAN

 * @Project IntelliJ IDEA

 * @Date 25.05.2022

 */

@RestController

@RequestMapping("/api/v2/transactions")

@RequiredArgsConstructor

public class TransactionController {



    private final TransactionFacade transactionFacade;



    @PostMapping

    public ResponseEntity<?> sendMoney(@RequestBody TransactionRequestDTO transactionRequestDTO){

        return transactionFacade.sendMoney(transactionRequestDTO);

    }



    @GetMapping("/{id}")

    public ResponseEntity<?> getTransaction(@PathVariable Long id){

        return transactionFacade.getTransaction(id);

    }





}



