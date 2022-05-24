package com.example.bankingsystem.model.entity;


import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.TransferType;
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
public class Transaction extends BaseModel {

    private String senderIBAN;
    private String receiverIBAN;
    private BigDecimal transferAmount;
    @Enumerated(value = EnumType.STRING)
    private TransferType transferType;

    private String transferDate;


  /*  @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

*/



}
