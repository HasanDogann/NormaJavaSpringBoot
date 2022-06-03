package com.example.bankingsystem.model.entity;


import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseModel {

    @Pattern(regexp = "[1-9]\\d{15}")
    @Column(nullable = false)
    private String cardNo;

    @Column(nullable = false)
    private BigDecimal cardBalance=BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal cardLimit=BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal cardDebt=BigDecimal.ZERO;

    private boolean isBlocked=false;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

}
