package com.example.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(unique = true)
    private String eMail;
    @NotNull
    private String password;
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

    public void addCardToCustomer(Set<Card> cardList1) {
        cardList.addAll(cardList1);

    }

    public void removeCardFromCustomer(Set<Card> cardList2) {
        cardList.removeAll(cardList2);

    }


}
