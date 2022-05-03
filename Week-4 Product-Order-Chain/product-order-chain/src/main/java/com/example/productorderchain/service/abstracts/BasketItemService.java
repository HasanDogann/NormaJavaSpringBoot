package com.example.productorderchain.service.abstracts;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface BasketItemService {

    Result createBasketItem(CreateBasketItemRequestDTO createBasketItemRequestDTO);

    SuccessDataResult<GetBasketItemResponseDTO> getBasketItem(Long id) throws BaseException;

    Collection<GetBasketItemResponseDTO> getAllBasketItems();

    Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException;


}
