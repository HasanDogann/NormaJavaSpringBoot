package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.UserCreateRequestByUserDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 20.05.2022
 */
public interface SigningService {

    String login(UserLoginRequest loginRequest);

    String register(UserCreateRequestByUserDTO userCreateRequestDTO);
}
