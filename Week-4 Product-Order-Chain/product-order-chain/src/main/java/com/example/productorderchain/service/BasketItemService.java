package com.example.productorderchain.service;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface BasketItemService {

    Result createBasketItem(CreateBasketItemRequestDTO createBasketItemRequestDTO);

    SuccessDataResult<GetBasketItemResponseDTO> getBasketItem(Long id) throws BaseException;

    SuccessDataResult<Collection<GetBasketItemResponseDTO>> getAllBasketItems();

    Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException;


}
