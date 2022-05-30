package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.AuthenticationFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.service.SigningService;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 *  20.05.2022
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;
    private final CustomerService customerService;
    private final SigningService signingService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginRequest loginRequest) {

     return authenticationFacade.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        /*User user = userService.getUserByEmail(userCreateRequestDTO.mail());
        String mailCheck = customerService.getCustomer(userCreateRequestDTO.customerId()).getEMail();
        if (user != null) {
            return new ResponseEntity<>("Email is already in use with another Customer", HttpStatus.BAD_REQUEST);
        }
        else if(!userCreateRequestDTO.mail()
                .equals(mailCheck)){
            return new ResponseEntity<>("Given email doesn't match with Customer email",HttpStatus.BAD_REQUEST);
        }
        else {
            signingService.register(userCreateRequestDTO);
            return new ResponseEntity<>("User is registered successfully", HttpStatus.CREATED);
        }*/
        return authenticationFacade.register(userCreateRequestDTO);
    }
}
