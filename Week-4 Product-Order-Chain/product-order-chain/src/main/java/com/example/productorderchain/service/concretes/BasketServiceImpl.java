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
       //burada kaldım
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
        System.out.println("old basket price is : "+oldPrice );
        b.setTotalPrice(oldPrice.add(basketItem.getPrice())
        );
        System.out.println("new total basket price is "+b.getTotalPrice());
        return b.getTotalPrice();
    }

    @Override
    public BigDecimal calcBasketTotalTaxPrice(Long id, BasketItem basketItem) {
        Basket b = getBasket(id);
        BigDecimal oldTaxPrice = b.getTaxPrice();
        System.out.println("Basket of oldTaxPrice "+b.getTaxPrice());
        b.setTaxPrice(oldTaxPrice.add(basketItem.getTaxPrice()));
        System.out.println("Basket of NewTaxPrice"+b.getTaxPrice());

        return b.getTaxPrice();
    }

    @Override
    public BigDecimal calcBasketTotalDiscountPrice(Long id, BasketItem basketItem) {

        Basket b = getBasket(id);
        BigDecimal oldDiscPrice = b.getDiscountPrice();
        System.out.println("Basket of oldDiscPrice "+b.getDiscountPrice());
        b.setDiscountPrice(oldDiscPrice.add(basketItem.getDiscountPrice()));
        System.out.println("Basket of NewDiscPrice "+b.getDiscountPrice());
        return b.getDiscountPrice();
    }

    @Override
    public BigDecimal calcBasketTotalShipmentPrice(Long id, BasketItem basketItem) {
        Basket b = getBasket(id);
        BigDecimal oldShipPrice = b.getShippingPrice();
        System.out.println("Basket of oldShipPrice "+b.getShippingPrice());
        b.setShippingPrice(oldShipPrice.add(basketItem.getShippingPrice()));
        System.out.println("basket item added with ship price is : "+basketItem.getShippingPrice());
        System.out.println("Basket of NewShipPrice "+b.getShippingPrice());

        return b.getShippingPrice();

    }

    public  void deleteBasketItemAllPricesFromBasket(Long id,BasketItem basketItem){
        Basket b = getBasket(basketItem.getBasket().getId());
        BigDecimal oldPrice = getBasket(basketItem.getBasket().getId()).getTotalPrice();
        System.out.println("old price"+oldPrice);
        BigDecimal oldDiscountPrice = getBasket(basketItem.getBasket().getId()).getDiscountPrice();
        System.out.println("oldDİsc"+oldDiscountPrice);
        BigDecimal oldTaxPrice = getBasket(basketItem.getBasket().getId()).getTaxPrice();
        System.out.println("oldTax"+oldTaxPrice);
        BigDecimal oldShipPrice = getBasket(basketItem.getBasket().getId()).getShippingPrice();
        System.out.println("oldShip"+oldShipPrice);

        b.setTotalPrice(oldPrice.subtract(basketItem.getPrice()));
        System.out.println("new price"+b.getTotalPrice());
        b.setDiscountPrice(oldDiscountPrice.subtract(basketItem.getDiscountPrice()));
        System.out.println("new disc."+b.getDiscountPrice());
        b.setTaxPrice(oldTaxPrice.subtract(basketItem.getTaxPrice()));
        System.out.println("new tax"+b.getTaxPrice());
        b.setShippingPrice(oldShipPrice.subtract(basketItem.getTaxPrice()));
        System.out.println("new ship"+b.getShippingPrice());

    }


}

