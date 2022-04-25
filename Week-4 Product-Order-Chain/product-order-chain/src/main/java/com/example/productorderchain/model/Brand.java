package com.example.productorderchain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Brand extends BaseModel {

    @OneToOne
    private Brand parent;

    private String name;

    private String brandCountry;

}
