package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;

public interface BasketConverter {

    Basket toBasket(CreateBasketRequestDTO createBasketRequestDTO);

    CreateBasketRequestDTO toCreateBasketRequestDTO(Basket basket);

    GetBasketResponseDTO toGetBasketResponseDTO(Basket basket);
}
