package com.example.productorderchain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BasketItem extends BaseModel {

    @JoinColumn(name = "basket_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade=CascadeType.PERSIST)
    private Basket basket;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
    private Product product;

    @Column(nullable = false)
    private BigDecimal quantity;
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;


    public BigDecimal totalBasketItemPrice() {
        this.price=product.getPrice().
                multiply(this.quantity).
                subtract(product.getDiscountRate().
                        multiply(price).
                        divide(BigDecimal.valueOf(100), RoundingMode.FLOOR));
        return price;
    }
    public BigDecimal totalBasketItemPriceDiscount(){
        this.discountPrice=product.getPrice().subtract((product.getPrice().multiply(product.getDiscountRate()).divide(BigDecimal.valueOf(100))));
        return discountPrice;
    }
    public BigDecimal totalBasketItemTaxPrice(){
        return taxPrice;
    }
    public BigDecimal totalBasketItemShippingPrice(){
        return shippingPrice;
    }
}
