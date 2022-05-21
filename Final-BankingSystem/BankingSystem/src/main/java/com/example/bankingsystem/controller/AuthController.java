package com.example.bankingsystem.controller;

import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.security.JsonWTokenProvider;
import com.example.bankingsystem.service.CustomerLoginService;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JsonWTokenProvider jsonWTokenProvider;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerLoginService customerLoginService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {

     return customerLoginService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        Customer customer = customerService.getCustomerByEmail(userRegisterRequest.email());
        if (customer != null) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
        } else {
            customerLoginService.register(userRegisterRequest);
            return new ResponseEntity<>("User is registered successfully", HttpStatus.CREATED);
        }
    }


}
