package com.example.productorderchain.dto.model;

import java.math.BigDecimal;

public record BasketItemDTO(BigDecimal quantity,
                            BigDecimal price,
                            BigDecimal discountPrice,
                            BigDecimal taxPrice,
                            BigDecimal shippingPrice,
                            Long productId,
                            Long basketID) {
}
