package com.example.productorderchain.dto.process.get;


import com.example.productorderchain.model.BasketItem;
import java.util.Set;


public record GetBasketResponseDTO(Long basketId,
                                   Set<BasketItem> BasketItems) {
}
