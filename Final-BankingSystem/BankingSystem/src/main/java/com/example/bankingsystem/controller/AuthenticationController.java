package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.AuthenticationFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestByUserDTO;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.SigningService;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Hasan DOĞAN
 * IntelliJ IDEA
 * 20.05.2022
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationFacade authenticationFacade;


    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginRequest loginRequest) {
        logger.trace("Post method used for Login User: {}",loginRequest.email());
        return authenticationFacade.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserCreateRequestByUserDTO userDTO) {
        logger.trace("Post method used for Register User: {}",userDTO.mail());
        return authenticationFacade.register(userDTO);
    }
}
