package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BasketItemConverter;
import com.example.productorderchain.converter.abstracts.ProductConverter;
import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.service.abstracts.BasketService;
import com.example.productorderchain.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BasketItemConverterImpl implements BasketItemConverter {
    private final ProductService productService;
    private final BasketService basketService;




    @Override
    public BasketItem toBasketItem(CreateBasketItemRequestDTO createBasketItemRequestDTO) {
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basketService.getBasket(createBasketItemRequestDTO.BasketID()));
        basketItem.setProduct(productService.getProduct(createBasketItemRequestDTO.productID()));

        basketItem.setDiscountPrice(((productService.getProduct(createBasketItemRequestDTO.productID()).getDiscountRate())
                .multiply(productService.getProduct(createBasketItemRequestDTO.productID()).getPrice()))
                .divide(BigDecimal.valueOf(100)));
        System.out.println("created basket item with Discprice "+basketItem.getDiscountPrice());
        basketItem.setQuantity(createBasketItemRequestDTO.quantity());
        System.out.println("basket ıtem quantity is created "+basketItem.getQuantity());
        basketItem.setShippingPrice(createBasketItemRequestDTO.shippingPrice());
        System.out.println("created basket item with Shipprice "+basketItem.getShippingPrice());
        basketItem.setTaxPrice(createBasketItemRequestDTO.taxPrice());
        System.out.println("created basket item with Taxprice "+basketItem.getTaxPrice());

        basketItem.setPrice((productService.getProduct(createBasketItemRequestDTO.productID()).getPrice().multiply(basketItem.getQuantity()))
                .add(basketItem.getTaxPrice())
                .add(basketItem.getShippingPrice())
                .subtract(basketItem.getDiscountPrice()));

        System.out.println("created basket item with price"+basketItem.getPrice());



/*
        BigDecimal oldShipPrice = basketService.getBasket(createBasketItemRequestDTO.BasketID()).getShippingPrice();
        BigDecimal oldTaxPrice = basketService.getBasket(createBasketItemRequestDTO.BasketID()).getTaxPrice();
        basketService.getBasket(createBasketItemRequestDTO.BasketID()).setShippingPrice(oldShipPrice.add(createBasketItemRequestDTO.shippingPrice()));
        basketService.getBasket(createBasketItemRequestDTO.BasketID()).setTaxPrice(oldTaxPrice.add(createBasketItemRequestDTO.taxPrice()));*/


        basketService.calcBasketTotalPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalDiscountPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalTaxPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalShipmentPrice(createBasketItemRequestDTO.BasketID(), basketItem);

        return basketItem;
    }

    @Override
    public CreateBasketItemRequestDTO toCreateBasketItemRequest(BasketItem basketItem) {

        return new CreateBasketItemRequestDTO( basketItem.getProduct().getId(),
                basketItem.getQuantity(),
                basketItem.getTaxPrice(),
                basketItem.getShippingPrice(),
                basketItem.getBasket().getId());

    }

    @Override
    public GetBasketItemResponseDTO toGetBasketItemResponse(BasketItem basketItem) {

        return new GetBasketItemResponseDTO(basketItem.getId(),
                basketItem.getQuantity(),
                basketItem.getPrice(),
                basketItem.getDiscountPrice(),
                basketItem.getShippingPrice(),
                basketItem.getProduct().getName());

    }
}