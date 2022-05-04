package com.example.productorderchain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Basket extends BaseModel {

    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "basket")
    private Set<BasketItem> items = new HashSet<>();

    @JsonIgnore
    @OneToOne
    private Order order;
}
