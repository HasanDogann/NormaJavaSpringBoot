package com.example.productorderchain.dto.process.get;

import com.example.productorderchain.model.OrderStatus;
import com.example.productorderchain.model.PaymentMethod;

import java.math.BigDecimal;

public record GetOrderResponseDTO(Long orderNumber,
                                  BigDecimal orderTotalPrice,
                                  BigDecimal orderTaxPrice,
                                  BigDecimal orderShipmentPrice,
                                  String paymentInfo,
                                  String orderDate,
                                  PaymentMethod paymentMethod,
                                  OrderStatus orderStatus,
                                  Long orderId) {
}
