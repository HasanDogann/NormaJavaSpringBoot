package com.example.productorderchain.dto.process.get;

import java.math.BigDecimal;

public record GetBasketItemResponseDTO(Long BasketItemId,
                                       BigDecimal quantity,
                                       BigDecimal price,
                                       BigDecimal discountPrice,
                                       BigDecimal shippingPrice,
                                       String productName) {
}
