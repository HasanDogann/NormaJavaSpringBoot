package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
public interface SigningService {

    public String login(UserLoginRequest loginRequest);
    public String register(UserCreateRequestDTO userCreateRequestDTO);
}
