package com.example.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Customer extends BaseModel{

    String name;
    String surname;
    String eMail;
    String phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer",orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Account> accountList=new HashSet<>();

    @OneToMany(mappedBy = "customer",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Card> cardList;


    public void addAccountToCustomer(Set<Account> accountList2){
        accountList.addAll(accountList2);

    }



}
