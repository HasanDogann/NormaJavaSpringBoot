package com.example.productorderchain.dto.process;

import java.math.BigDecimal;

public record GetBasketItemResponseDTO(BigDecimal quantity,
                                       BigDecimal price,
                                       BigDecimal discountPrice,
                                       BigDecimal shippingPrice,
                                       String productName) {
}
