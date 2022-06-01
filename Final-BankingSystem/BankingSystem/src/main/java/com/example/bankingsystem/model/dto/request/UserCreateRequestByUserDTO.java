package com.example.bankingsystem.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public record UserCreateRequestByUserDTO(@Email(message = "Email doesn't have a valid format") String mail,
                                         @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*[\\d])+)(?=(.*[\\W])+)(?!.*\\s).{8,}$",
                                                 message = "Password must have 8 char. 1 Upper 1 special 1 Lower and 1 digit") String password,
                                         @Min(value = 1, message = "Customer ID must be bigger than 0") Long customerId) {
}
