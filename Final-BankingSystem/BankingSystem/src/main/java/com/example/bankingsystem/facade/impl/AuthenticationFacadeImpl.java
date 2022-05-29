package com.example.bankingsystem.facade.impl;

import com.example.bankingsystem.facade.AuthenticationFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.SigningService;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 29.05.2022
 */
@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final CustomerService customerService;
    private final SigningService signingService;
    private final UserService userService;

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        return signingService.login(userLoginRequest);
    }

    @Override
    public ResponseEntity<String> register(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userService.getUserByEmail(userCreateRequestDTO.mail());
        String mailCheck = customerService.getCustomer(userCreateRequestDTO.customerId()).getEMail();
        if (user != null) {
            return new ResponseEntity<>("Email is already in use with another Customer", HttpStatus.BAD_REQUEST);
        } else if (!userCreateRequestDTO.mail()
                .equals(mailCheck)) {
            return new ResponseEntity<>("Given email doesn't match with Customer email", HttpStatus.BAD_REQUEST);
        } else {
            signingService.register(userCreateRequestDTO);
            return new ResponseEntity<>("User is registered successfully", HttpStatus.CREATED);
        }
    }


}
