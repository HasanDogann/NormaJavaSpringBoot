package com.example.productorderchain.dto.process;

import com.example.productorderchain.model.BasketItem;
import java.util.Set;


public record CreateBasketRequestDTO(Set<BasketItem> basketItemSet,
                                     Long basketID) {
}
