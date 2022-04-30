package com.example.productorderchain.dto.process;

import java.math.BigDecimal;

public record CreateBasketItemRequestDTO(BigDecimal quantity,
                                         BigDecimal price,
                                         BigDecimal discountPrice,
                                         BigDecimal taxPrice,
                                         BigDecimal shippingPrice,
                                         Long productID,
                                         Long BasketID) {
}
