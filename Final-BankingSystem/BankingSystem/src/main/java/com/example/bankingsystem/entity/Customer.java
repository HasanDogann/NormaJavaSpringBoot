package com.example.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseModel{

    String name;
    String surname;
    String Email;
    String Phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer",orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Account> accountList;

    @OneToMany(mappedBy = "customer",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Card> cardList;


    @OneToMany(mappedBy = "customer",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transfer> transfersList;



}
