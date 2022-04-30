package com.example.productorderchain.service;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.GetBasketResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.Brand;
import com.example.productorderchain.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

private final BasketRepository basketRepository;

    @Override
    public Result addBasketItem(CreateBasketRequestDTO basketRequestDTO) {
       //burada kaldım
      

        return new SuccessResult("Basket item is added successfully");
    }

    @Override
    public SuccessDataResult<GetBasketResponseDTO> getBasketItem(Long id) throws BaseException {
      return null;
    }

    @Override
    public SuccessDataResult<Collection<GetBasketResponseDTO>> getAllBasketItems() {
        return null;
    }

    @Override
    public Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException {
        return null;
    }


}
