package com.example.bankingsystem.model.entity;


import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseModel {

    @Column(nullable = false)
    private String senderIBAN;
    @Column(nullable = false)
    @Pattern(regexp = "TR\\d{24}|[1-9]\\d{15}")
    private String receiverIBAN;
    @Column(nullable = false)
    @Min(value = 1)
    private BigDecimal transferAmount;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TransferType transferType;
    @Column(nullable = false)
    private String transferDate;




}
