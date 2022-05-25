package com.example.bankingsystem.converter;

import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserResponseDTO;
import com.example.bankingsystem.model.entity.User;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */
public interface UserConverter {

    User toUser(UserCreateRequestDTO createUserDTO);

    UserResponseDTO toUserResponseFromUser(User user);
}
