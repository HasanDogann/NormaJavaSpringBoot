package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.model.dto.request.UserCreateDTO;
import com.example.bankingsystem.model.dto.request.UserLoginRequest;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.security.JsonWTokenProvider;
import com.example.bankingsystem.service.SigningService;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.UserService;
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
public class SigningServiceImpl implements SigningService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JsonWTokenProvider jsonWTokenProvider;
    private final UserService userService;



    //Burada kaldım login kontrolleri yapılacak
    @Override
    public String login(UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken
                        (loginRequest.email(), loginRequest.password());
        log.info(authenticationToken + "auth token");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info(authentication.toString() + "auth");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(authentication.getAuthorities().toString());
        return "Bearer " + jsonWTokenProvider.generateJWToken(authentication);
    }

    @Override
    public String register(UserCreateDTO userCreateDTO) {
        //1000.ci register olan web userına 1000 TL bakiye eklenecek
        Customer customer = customerService.getCustomer(userCreateDTO.customerId());
        if(userCreateDTO.mail().equals(customer.getEMail())){
            User user = new User();
        user.setMail(customer.getEMail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.password()));
        user.setRole(userCreateDTO.role());
        user.setCustomer(customerService.getCustomer(userCreateDTO.customerId()));
        userService.addUser(new UserCreateDTO(user.getMail(), user.getPassword(), user.getRole(), user.getCustomer().getId()));
        return null;
        }
        return "Wrong!";
    }
}
