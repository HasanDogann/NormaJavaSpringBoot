package com.example.bankingsystem.dto.request;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
public record UserRequest(String email,
                          String password,
                          CustomerCreateRequestDTO customerCreateRequestDTO) {
}
