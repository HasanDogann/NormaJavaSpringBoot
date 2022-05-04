package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
import com.example.productorderchain.model.BasketItem;

public interface BasketItemConverter {

    BasketItem toBasketItem(CreateBasketItemRequestDTO basketItemDTO);


    GetBasketItemResponseDTO toGetBasketItemResponse(BasketItem basketItem);
}
