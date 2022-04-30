package com.example.productorderchain.dto.process;

import com.example.productorderchain.dto.model.BasketItemDTO;



public record CreateBasketRequestDTO(BasketItemDTO basketItemDTO,
                                     Long productId,
                                     Long basketID) {
}
