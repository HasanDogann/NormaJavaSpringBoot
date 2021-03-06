package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.exception.SignOperationException;
import com.example.bankingsystem.model.dto.request.UserCreateRequestByUserDTO;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.model.entity.enums.Role;
import com.example.bankingsystem.security.JsonWTokenProvider;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.SigningService;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 20.05.2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SigningServiceImpl implements SigningService {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JsonWTokenProvider jsonWTokenProvider;
    private final UserService userService;


    @Override
    public String login(UserLoginRequest loginRequest) {
        Customer customer = customerService.getCustomerByEmail(loginRequest.email());
        if (Objects.isNull(customer)) {
            throw new SignOperationException.LoginBadCredentialsException("Email is not registered");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken
                        (loginRequest.email(), loginRequest.password());
        log.info(authenticationToken + "auth token");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info(authentication.toString() + "auth");
        log.info(authentication.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(authentication.getAuthorities().toString());
        return "Bearer " + jsonWTokenProvider.generateJWToken(authentication);
    }

    @Override
    public String register(UserCreateRequestByUserDTO userCreateRequestDTO) {
        Customer customer = customerService.getCustomer(userCreateRequestDTO.customerId());
        if (userCreateRequestDTO.mail().equals(customer.getMail())) {
            User user = new User();
            user.setMail(customer.getMail());
            user.setPassword(userCreateRequestDTO.password());
            user.setRole(Role.valueOf("USER"));
            user.setCustomer(customerService.getCustomer(userCreateRequestDTO.customerId()));
            userService.addUser(new UserCreateRequestDTO(user.getMail(), user.getPassword(), user.getRole(), user.getCustomer().getId()));
            return null;
        }
        return "Wrong email!";
    }
}
