package com.example.productorderchain.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product extends BaseExtendedModel {

    private String name;
    private BigDecimal price;
    private UUID barcode;
    private String image;

    @ManyToOne(cascade=CascadeType.ALL)
    private Brand brand;
    @ManyToOne(cascade=CascadeType.ALL)
    private Category category;


}
