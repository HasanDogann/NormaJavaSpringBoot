package com.example.productorderchain.service;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.*;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface BasketService {
    Result addBasketItem(CreateBasketRequestDTO basketItemRequestDTO);

    SuccessDataResult<GetBasketResponseDTO> getBasketItem(Long id) throws BaseException;

    SuccessDataResult<Collection<GetBasketResponseDTO>> getAllBasketItems();

    Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException;



}
