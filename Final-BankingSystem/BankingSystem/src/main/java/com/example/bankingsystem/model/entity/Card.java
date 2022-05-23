package com.example.bankingsystem.model.entity;


import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.CardType;
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
public class Card extends BaseModel {

    private String cardNo;

    private BigDecimal cardBalance=BigDecimal.ZERO;

    private BigDecimal cardLimit=BigDecimal.ZERO;

    private BigDecimal cardDebt=BigDecimal.ZERO;

    private boolean isBlocked=false;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

}
