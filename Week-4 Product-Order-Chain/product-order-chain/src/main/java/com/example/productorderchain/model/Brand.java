package com.example.productorderchain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Brand extends BaseModel {

    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL)
    private Brand parent;

    private String name;

    private String brandCountry;

}
