package com.example.bankingsystem.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress extends BaseModel{



    private String country;
    private String city;
    private String postalCode;
    private String description;

    @OneToOne
    private Customer customer;


}
