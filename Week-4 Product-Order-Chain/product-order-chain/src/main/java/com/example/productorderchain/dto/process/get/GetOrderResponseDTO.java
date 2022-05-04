package com.example.productorderchain.dto.process.get;

import com.example.productorderchain.model.CustomerAddress;
import com.example.productorderchain.model.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

public record GetOrderResponseDTO(Long orderNumber,
                                  BigDecimal orderTotalPrice,
                                  BigDecimal orderTaxPrice,
                                  BigDecimal orderShipmentPrice,
                                  CustomerAddress shippingAddress,
                                  String paymentInfo,
                                  String orderDate,
                                  OrderStatus orderStatus,
                                  Long orderId) {
}
