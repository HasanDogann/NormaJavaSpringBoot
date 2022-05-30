package com.example.bankingsystem.model.dto.request;

import com.example.bankingsystem.model.entity.enums.PaymentType;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public record CardPaymentRequestDTO( @Min(value = 1,message = "Amount must be bigger than 0") @Column(nullable = false) BigDecimal amount,
                                    @Pattern(regexp = "[1-9]\\d{15}",message = "Card No pattern must match with :")
                                    @Column(nullable = false)
                                    String cardNo,
                                    @Column(nullable = false)
                                    PaymentType paymentType) {
}
