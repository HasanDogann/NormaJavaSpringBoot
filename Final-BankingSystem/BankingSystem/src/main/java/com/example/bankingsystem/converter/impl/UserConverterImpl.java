package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.model.dto.request.UserCreateDTO;
import com.example.bankingsystem.model.dto.response.UserResponseDTO;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */
@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final CustomerService customerService;

    @Override
    public User toUser(UserCreateDTO userCreateDTO) {
        User user= new User();
        Customer customer = customerService.getCustomer(userCreateDTO.customerId());
        user.setMail(customer.getEMail());
        user.setPassword(userCreateDTO.password());
        user.setRole(userCreateDTO.role());
        user.setCustomer(customer);
        return user;
    }

    @Override
    public UserResponseDTO toUserResponseFromUser(User user) {
        return new UserResponseDTO(user.getId(), user.getMail(),user.getRole(),
                user.getCustomer().getName(),user.getCustomer().getSurname());
    }
}
