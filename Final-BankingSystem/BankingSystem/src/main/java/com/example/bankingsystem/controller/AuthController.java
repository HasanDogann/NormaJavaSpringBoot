package com.example.bankingsystem.controller;

import com.example.bankingsystem.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.UserRequest;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;
import com.example.bankingsystem.security.JsonWTokenProvider;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JsonWTokenProvider jsonWTokenProvider;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody UserRequest loginRequest){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Bearer "+jsonWTokenProvider.generateJWToken(authentication);


    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest){
      Customer customer = customerService.getCustomerByEmail(userRequest.email());
      if(customer!=null){
          return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
      }
      else{
      Customer customer1 = new Customer();
      customer1.setEMail(userRequest.email());
      customer1.setPassword(passwordEncoder.encode(userRequest.password()));

        customerService.addCustomer(userRequest.customerCreateRequestDTO());
      return new ResponseEntity<>("User is registered successfully",HttpStatus.CREATED);
    }}



}
