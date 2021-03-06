package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserGetResponseDTO;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 21.05.2022
 */
@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final CustomerService customerService;

    @Override
    public User toUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = new User();
        Customer customer = customerService.getCustomer(userCreateRequestDTO.customerId());
        if (!userCreateRequestDTO.mail().equals(customer.getMail())) {
            throw new ServiceOperationCanNotCreateException.UserCanNotCreatException("Customer email is wrong!");
        }
        user.setMail(customer.getMail());
        user.setPassword(userCreateRequestDTO.password());
        user.setRole(userCreateRequestDTO.role());
        user.setCustomer(customer);
        return user;
    }

    @Override
    public UserGetResponseDTO toUserResponseFromUser(User user) {
        return new UserGetResponseDTO(user.getId(), user.getMail(), user.getRole(),
                user.getCustomer().getName(), user.getCustomer().getSurname());
    }
}
