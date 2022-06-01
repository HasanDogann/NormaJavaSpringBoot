package com.example.bankingsystem.model.entity;

import com.example.bankingsystem.model.entity.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customers")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Customer extends BaseModel {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Email
    @Column(unique = true)
    private String mail;

    @NotNull
    private String phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Account> accountList = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Card> cardList;


    public void addAccountToCustomer(Set<Account> accountList2) {
        accountList.addAll(accountList2);

    }

    public void removeAccountFromCustomer(Set<Account> accountList3) {
        accountList.removeAll(accountList3);

    }



}
