package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public record AccountCreateRequestDTO(@Column(nullable = false) AccountType accountType,
                                      @Column(nullable = false) BalanceCurrencyType balanceCurrencyType,
                                      @Column(nullable = false)   @Range(min = 1,max = 9999,message = "Branch code must be between 1 and 9999")
                                       Integer branchCode,
                                      @Min(value = 1,message = "Customer ID must be bigger than 0")
                                      Long customerId) {
}
