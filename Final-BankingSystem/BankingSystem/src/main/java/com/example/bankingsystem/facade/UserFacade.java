package com.example.bankingsystem.facade;

import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserGetResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 28.05.2022
 */
public interface UserFacade {

    ResponseEntity<String> addUser(UserCreateRequestDTO userCreateRequestDTO);

    ResponseEntity<UserGetResponseDTO> getUser(Long id);

    ResponseEntity<Collection<UserGetResponseDTO>> getAllUsers();

    ResponseEntity<String> deleteUser(Long id, boolean isHardDelete);

}
