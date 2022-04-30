package com.example.productorderchain.service;

import com.example.productorderchain.converter.BasketConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.GetBasketResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.Brand;
import com.example.productorderchain.model.Product;
import com.example.productorderchain.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

private final BasketRepository basketRepository;
private final BasketConverter basketConverter;

    @Override
    public Result addBasketItem(CreateBasketRequestDTO createBasketRequestDTO) {
       //burada kaldım
      Basket basket= basketConverter.toBasket(createBasketRequestDTO);
      basketRepository.save(basket);

        return new SuccessResult("Basket item is added successfully");
    }

    @Override
    public GetBasketResponseDTO getBasketItem(Long id) throws BaseException {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Product not found"));
        if (basket.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Basket was deleted");
        }
      return (basketConverter.toGetBasketResponseDTO(basket));
    }

    @Override
    public SuccessDataResult<Collection<GetBasketResponseDTO>> getAllBasketItems() {
        return new SuccessDataResult<>( basketRepository
                .findAllBasketByDeleteStatusByJPQL(false)
                .stream()
                .map(basketConverter::toGetBasketResponseDTO)
                .toList(),"Basket and items are listed successfully");

    }

    @Override
    public Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException {
        Basket basket = basketRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Basket is not found"));
        if (basket.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Basket is already deleted");
        }
        if (hardDelete) {
            basketRepository.delete(basket);
            return new SuccessResult("Basket "+basket.getId()+" is deleted with HardDelete successfully");
        }
        basket.setDeleted(true);
        basketRepository.save(basket);
        return new SuccessResult("Basket "+basket.getId()+" is deleted with SoftDelete successfully");
    }
    }

