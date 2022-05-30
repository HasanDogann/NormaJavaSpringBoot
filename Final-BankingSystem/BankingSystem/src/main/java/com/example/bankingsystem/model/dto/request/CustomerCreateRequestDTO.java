package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.model.dto.model.CustomerAddressDTO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public record CustomerCreateRequestDTO(@Column(nullable = false)String customerName,
                                       @Column(nullable = false)String customerSurname,
                                       @Email String customerEmail,
                                       String customerPhone,
                                       @Column(nullable = false) @Range(max = 9999,min = 1,message = "Branch code must be between 1 and 9999") Integer branchCode,
                                       @Column(nullable = false)CustomerAddressDTO customerAddress
                                       ) {
}
