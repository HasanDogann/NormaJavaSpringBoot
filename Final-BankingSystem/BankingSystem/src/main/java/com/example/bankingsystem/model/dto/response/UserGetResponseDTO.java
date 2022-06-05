package com.example.bankingsystem.model.dto.response;

import com.example.bankingsystem.model.entity.enums.Role;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 22.05.2022
 */
public record UserGetResponseDTO(Long userId,
                                 String mail,
                                 Role role,
                                 String name, String surname) {
}
