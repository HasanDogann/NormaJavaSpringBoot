package com.example.productorderchain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseModel{


    private Long orderNumber;
    private BigDecimal orderTotalPrice;
    private BigDecimal orderTaxPrice;
    private BigDecimal orderShipmentPrice;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerShippingAddress;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerBillingAddress;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String paymentInfo;

    private String orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.NEW;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    private Basket basket;
}
