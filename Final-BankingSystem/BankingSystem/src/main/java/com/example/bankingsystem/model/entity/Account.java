package com.example.bankingsystem.model.entity;

import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseModel {


    private String IBAN;
    private Long accountNumber;
    private BigDecimal balance=BigDecimal.ZERO;
    private String creationDate;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @Enumerated(value = EnumType.STRING)
    private BalanceType balanceType;



    @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(mappedBy = "account",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Card> cardList;

/*

    @OneToMany(mappedBy = "account",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transfersList;

*/


}
