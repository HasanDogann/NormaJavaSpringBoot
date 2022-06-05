package com.example.bankingsystem.facade.impl;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.facade.UserFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserGetResponseDTO;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 28.05.2022
 */
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    public ResponseEntity<String> addUser(UserCreateRequestDTO userCreateRequestDTO) {
        userService.addUser(userCreateRequestDTO);
        return ResponseEntity.ok().body("User is created successfully");
    }

    @Override
    public ResponseEntity<UserGetResponseDTO> getUser(Long id) {
        UserGetResponseDTO responseDTO = userConverter.toUserResponseFromUser(
                userService.getUser(id));
        return ResponseEntity.ok().body(responseDTO);
    }


    @Override

    public ResponseEntity<Collection<UserGetResponseDTO>> getAllUsers() {
        Collection<UserGetResponseDTO> responseDTOS = userService.getAllUsers()
                .stream()
                .map(userConverter::toUserResponseFromUser).toList();
        if (responseDTOS.isEmpty()) {
            throw new ServiceOperationNotFoundException.UserNotFoundException("There is no user");
        }
        return ResponseEntity.ok().body(responseDTOS);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id, boolean isHardDelete) {
        return ResponseEntity.ok().body(userService.deleteUser(id, isHardDelete));
    }
}