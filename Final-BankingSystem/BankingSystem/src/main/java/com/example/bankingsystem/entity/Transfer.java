package com.example.bankingsystem.entity;


import com.example.bankingsystem.entity.enums.TransferType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer extends BaseModel{

    private BigDecimal transferNo;
    private BigDecimal transferAmount;
    @Enumerated
    private TransferType transferType;


    @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;





}
