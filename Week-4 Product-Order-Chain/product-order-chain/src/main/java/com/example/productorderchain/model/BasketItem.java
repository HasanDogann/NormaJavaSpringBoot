package com.example.productorderchain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Setter
public class BasketItem extends BaseModel {

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Basket basket;

    @ManyToOne(optional = false)
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
