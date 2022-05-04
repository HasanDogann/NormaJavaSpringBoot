package com.example.productorderchain.service.concretes;

import com.example.productorderchain.converter.abstracts.BasketConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.repository.BasketRepository;
import com.example.productorderchain.service.abstracts.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

private final BasketRepository basketRepository;
private final BasketConverter basketConverter;

    @Override
    public Result addBasket(CreateBasketRequestDTO createBasketRequestDTO) {
      Basket basket= basketConverter.toBasket(createBasketRequestDTO);
      basketRepository.save(basket);

      return new SuccessResult("Basket item is added successfully");
    }

    @Override
    public Basket getBasket(Long id) throws BaseException {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Basket is not found"));
        if (basket.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Basket was deleted");
        }

      return basket;
    }

    @Override
    public Collection<GetBasketResponseDTO> getAllBaskets() {
        return basketRepository
                .findAllBasketByDeleteStatusByJPQL(false)
                .stream()
                .map(basketConverter::toGetBasketResponseDTO)
                .toList();
    }

    @Override
    public Result deleteBasket(Long id, boolean hardDelete) throws BaseException {
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


    public BigDecimal calcBasketTotalPrice(Long id,BasketItem basketItem){
        Basket b = getBasket(basketItem.getBasket().getId());
        BigDecimal oldPrice = b.getTotalPrice();
        b.setTotalPrice(oldPrice.add(basketItem.getPrice()));
        return b.getTotalPrice();
    }

    @Override
    public BigDecimal calcBasketTotalTaxPrice(Long id, BasketItem basketItem) {
        Basket b = getBasket(id);
        BigDecimal oldTaxPrice = b.getTaxPrice();
        b.setTaxPrice(oldTaxPrice.add(basketItem.getTaxPrice()));

        return b.getTaxPrice();
    }

    @Override
    public BigDecimal calcBasketTotalDiscountPrice(Long id, BasketItem basketItem) {

        Basket b = getBasket(id);
        BigDecimal oldDiscPrice = b.getDiscountPrice();
        b.setDiscountPrice(oldDiscPrice.add(basketItem.getDiscountPrice()));

        return b.getDiscountPrice();
    }

    @Override
    public BigDecimal calcBasketTotalShipmentPrice(Long id, BasketItem basketItem) {
        Basket b = getBasket(id);
        BigDecimal oldShipPrice = b.getShippingPrice();
        b.setShippingPrice(oldShipPrice.add(basketItem.getShippingPrice()));

        return b.getShippingPrice();

    }

    public  void deleteBasketItemAllPricesFromBasket(Long id,BasketItem basketItem){
        Basket b = getBasket(basketItem.getBasket().getId());
        BigDecimal oldPrice = getBasket(basketItem.getBasket().getId()).getTotalPrice();
        BigDecimal oldDiscountPrice = getBasket(basketItem.getBasket().getId()).getDiscountPrice();
        BigDecimal oldTaxPrice = getBasket(basketItem.getBasket().getId()).getTaxPrice();
        BigDecimal oldShipPrice = getBasket(basketItem.getBasket().getId()).getShippingPrice();


        b.setTotalPrice(oldPrice.subtract(basketItem.getPrice()));
        b.setDiscountPrice(oldDiscountPrice.subtract(basketItem.getDiscountPrice()));
        b.setTaxPrice(oldTaxPrice.subtract(basketItem.getTaxPrice()));
        b.setShippingPrice(oldShipPrice.subtract(basketItem.getShippingPrice()));

    }


}

