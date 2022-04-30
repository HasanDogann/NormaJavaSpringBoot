package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.dto.process.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;

public interface BasketConverter {

    Basket toBasket(CreateBasketRequestDTO createBasketRequestDTO);

    CreateBasketRequestDTO toCreateBasketRequestDTO(Basket basket);

    GetBasketResponseDTO toGetBasketResponseDTO(Basket basket);
}
