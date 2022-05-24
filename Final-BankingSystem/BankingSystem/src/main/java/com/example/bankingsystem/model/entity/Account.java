package com.example.bankingsystem.model.entity;

import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseModel {


    @Column(unique = true)
    private String IBAN;
    @Column(length = 10)
    private Long accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    private BigDecimal lockedBalance = BigDecimal.ZERO;

    private String creationDate;
    @Column(length = 4)
    private int bankBranchCode;

    private boolean isDeficitAccount = false;


    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @Enumerated(value = EnumType.STRING)
    private BalanceCurrencyType balanceCurrencyType;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Card> cardList = new HashSet<>();


    public void addCardToAccount(Set<Card> cardList1) {
        cardList.addAll(cardList1);

    }

    public void removeCardFromAccount(Set<Card> cardList2) {
        cardList.removeAll(cardList2);

    }

}
