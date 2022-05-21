package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.security.JsonWTokenProvider;
import com.example.bankingsystem.service.CustomerLoginService;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerLoginServiceImpl implements CustomerLoginService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JsonWTokenProvider jsonWTokenProvider;

    @Override
    public String login(UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken
                        (loginRequest.email(),loginRequest.password());
        log.info(authenticationToken+"auth token");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info(authentication.toString()+"auth");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(jsonWTokenProvider.generateJWToken(authentication)+"jstprovider");
        return "Bearer " + jsonWTokenProvider.generateJWToken(authentication);
    }

    @Override
    public String register(UserRegisterRequest userRegisterRequest) {
        Customer customer = new Customer();
        customer.setEMail(userRegisterRequest.email());
        customer.setPassword(passwordEncoder.encode(userRegisterRequest.customerCreateRequestDTO().password()));
        customerService.addCustomer(new UserRegisterRequest(customer.getEMail(), customer.getPassword(), userRegisterRequest.customerCreateRequestDTO()));

        return null;
    }
}
