package com.example.productorderchain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
    private BigDecimal discountRate=BigDecimal.ZERO;

    @JsonIgnore
    @JoinColumn(name = "brand_id",nullable = false)
    @ManyToOne(cascade=CascadeType.ALL)
    private Brand brand;

    @JsonIgnore
    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne(cascade=CascadeType.ALL)
    private Category category;


}
