package com.example.productorderchain.service.abstracts;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;

import java.math.BigDecimal;
import java.util.Collection;

public interface BasketService {
    Result addBasket(CreateBasketRequestDTO basketItemRequestDTO);

    Basket getBasket(Long id) throws BaseException;

    Collection<GetBasketResponseDTO> getAllBaskets();

    Result deleteBasket(Long id, boolean hardDelete) throws BaseException;

     BigDecimal calcBasketTotalPrice(Long id, BasketItem basketItem);

    BigDecimal calcBasketTotalTaxPrice(Long id, BasketItem basketItem);

    BigDecimal calcBasketTotalDiscountPrice(Long id, BasketItem basketItem);

    BigDecimal calcBasketTotalShipmentPrice(Long id, BasketItem basketItem);

    void deleteBasketItemAllPricesFromBasket(Long id,BasketItem basketItem);



}
