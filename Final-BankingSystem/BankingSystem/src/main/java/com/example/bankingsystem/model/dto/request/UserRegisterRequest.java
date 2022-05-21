package com.example.bankingsystem.model.dto.request;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
public record UserRegisterRequest(String email,
                                  String password,
                                  CustomerCreateRequestDTO customerCreateRequestDTO) {
}
