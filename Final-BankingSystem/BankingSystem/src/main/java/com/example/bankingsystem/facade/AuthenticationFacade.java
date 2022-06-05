package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.UserCreateRequestByUserDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import org.springframework.http.ResponseEntity;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 29.05.2022
 */
public interface AuthenticationFacade {


    String login(UserLoginRequest userLoginRequest);

    ResponseEntity<String> register(UserCreateRequestByUserDTO userCreateRequestDTO);


}
