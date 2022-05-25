package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.Role;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */
public record UserCreateRequestDTO(String mail,
                                   String password,
                                   Role role,
                                   Long customerId) {
}
