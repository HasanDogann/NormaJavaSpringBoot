package com.example.productorderchain.dto.process.create;

import com.example.productorderchain.model.PaymentMethod;

public record CreateOrderRequestDTO(PaymentMethod paymentMethod,
                                    Long customerId,
                                    Long basketID) {
}
