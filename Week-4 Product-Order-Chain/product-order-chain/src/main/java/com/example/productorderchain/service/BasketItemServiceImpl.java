package com.example.productorderchain.service;

import com.example.productorderchain.converter.BasketItemConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.model.Product;
import com.example.productorderchain.repository.BasketItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemConverter basketItemConverter;
    private final BasketItemRepository basketItemRepository;

    @Override
    public Result createBasketItem(CreateBasketItemRequestDTO createBasketItemRequestDTO) {
       BasketItem basketItem = basketItemConverter.toBasketItem(createBasketItemRequestDTO);
        basketItemRepository.save(basketItem);
        return new SuccessResult("BasketItem "+basketItem.getProduct()+" is added successfuly");
    }

    @Override
    public SuccessDataResult<GetBasketItemResponseDTO> getBasketItem(Long id) throws BaseException {
        BasketItem basketItem=basketItemRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("BasketItem is not found"));
        if (basketItem.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("BasketItem was deleted");
        }
        return new SuccessDataResult<>( basketItemConverter.toGetBasketItemResponse(basketItem),"BasketItem is listed successfully");
    }

    @Override
    public SuccessDataResult<Collection<GetBasketItemResponseDTO>> getAllBasketItems() {

        return new SuccessDataResult<>( basketItemRepository
                .findAllProductsByDeleteStatusByJPQL(false)
                .stream()
                .map(basketItemConverter::toGetBasketItemResponse)
                .toList(),"BasketItems are listed successfully");

    }

    @Override
    public Result deleteBasketItem(Long id, boolean hardDelete) throws BaseException {
        BasketItem basketItem=basketItemRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("BasketItem is not found"));
        if (basketItem.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("BasketItem is already deleted");
        }
        if (hardDelete) {
            basketItemRepository.delete(basketItem);
            return new SuccessResult("BasketItem "+basketItem.getProduct().getName()+" is deleted with HardDelete successfully");
        }
        basketItem.setDeleted(true);
        basketItemRepository.save(basketItem);
        return new SuccessResult("BasketItem "+basketItem.getProduct().getName()+" is deleted with SoftDelete successfully");
    }
}
