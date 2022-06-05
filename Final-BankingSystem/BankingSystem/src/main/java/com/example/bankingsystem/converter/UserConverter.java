package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserGetResponseDTO;
import com.example.bankingsystem.model.entity.User;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 21.05.2022
 */
public interface UserConverter {

    User toUser(UserCreateRequestDTO createUserDTO);

    UserGetResponseDTO toUserResponseFromUser(User user);
}
