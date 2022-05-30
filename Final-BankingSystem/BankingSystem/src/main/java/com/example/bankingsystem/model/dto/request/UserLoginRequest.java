package com.example.bankingsystem.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
public record UserLoginRequest(@Email String email,
                               @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*[\\d])+)(?=(.*[\\W])+)(?!.*\\s).{8,}$",message = "Password must have 8 char. 1 Upper 1 special 1 Lower and 1 digit")
                               String password) {
}
