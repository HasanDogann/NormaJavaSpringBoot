package com.example.productorderchain.dto.process;

import java.math.BigDecimal;

public record CreateBasketItemRequestDTO(Long productID,
                                         BigDecimal quantity,
                                         BigDecimal taxPrice,
                                         BigDecimal shippingPrice,
                                         Long BasketID) {
}
