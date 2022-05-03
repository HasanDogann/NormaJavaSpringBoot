package com.example.productorderchain.service.concretes;

import com.example.productorderchain.converter.abstracts.BasketItemConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.repository.BasketItemRepository;
import com.example.productorderchain.service.abstracts.BasketItemService;
import com.example.productorderchain.service.abstracts.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemConverter basketItemConverter;
    private final BasketItemRepository basketItemRepository;
    private final BasketService basketService;

    @Override
    public Result createBasketItem(CreateBasketItemRequestDTO createBasketItemRequestDTO) {
       BasketItem basketItem = basketItemConverter.toBasketItem(createBasketItemRequestDTO);
        basketItemRepository.save(basketItem);
        return new SuccessResult("BasketItem "+basketItem.getProduct().getName()+" is added successfuly");
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
    public Collection<GetBasketItemResponseDTO> getAllBasketItems() {

        return basketItemRepository
                .findAllByIsDeleted(false)
                .stream()
                .map(basketItemConverter::toGetBasketItemResponse)
                .toList();
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
            basketItemRepository.deleteById(basketItem.getId());
            basketService.deleteBasketItemAllPricesFromBasket(id,basketItem);
            return new SuccessResult("BasketItem "+basketItem.getProduct().getName()+" is deleted with HardDelete successfully");
        }
        basketItem.setDeleted(true);
        basketItemRepository.save(basketItem);
        basketService.deleteBasketItemAllPricesFromBasket(id,basketItem);
        return new SuccessResult("BasketItem "+basketItem.getProduct().getName()+" is deleted with SoftDelete successfully");
    }
}
