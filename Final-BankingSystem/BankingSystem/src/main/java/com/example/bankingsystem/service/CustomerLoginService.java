package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
public interface CustomerLoginService {

    public String login(UserLoginRequest loginRequest);
    public String register(UserRegisterRequest userRegisterRequest);
}
