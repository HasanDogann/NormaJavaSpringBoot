package com.example.bankingsystem.model.entity;


import com.example.bankingsystem.model.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress extends BaseModel {



    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String postalCode;
    private String description;

    @OneToOne
    private Customer customer;


}
