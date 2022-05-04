package com.example.productorderchain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
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

    private String paymentMethod;
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
