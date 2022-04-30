package com.example.productorderchain.converter;

import com.example.productorderchain.dto.model.BasketItemDTO;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.model.BasketItem;

public interface BasketItemConverter {

    BasketItem toBasketItem(CreateBasketItemRequestDTO basketItemDTO);

    CreateBasketItemRequestDTO toCreateBasketItemRequest(BasketItem basketItem);

    GetBasketItemResponseDTO toGetBasketItemResponse(BasketItem basketItem);
}
