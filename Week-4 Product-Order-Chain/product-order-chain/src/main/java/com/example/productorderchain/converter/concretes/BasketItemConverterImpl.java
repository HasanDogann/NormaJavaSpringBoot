package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BasketItemConverter;
import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
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

        basketItem.setQuantity(createBasketItemRequestDTO.quantity());
        basketItem.setShippingPrice(createBasketItemRequestDTO.shippingPrice());
        basketItem.setTaxPrice(createBasketItemRequestDTO.taxPrice());

        basketItem.setPrice((productService.getProduct(createBasketItemRequestDTO.productID()).getPrice().multiply(basketItem.getQuantity()))
                .add(basketItem.getTaxPrice())
                .add(basketItem.getShippingPrice())
                .subtract(basketItem.getDiscountPrice()));
        //Basket is updated after adding new Basket Item
        basketService.calcBasketTotalPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalDiscountPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalTaxPrice(createBasketItemRequestDTO.BasketID(), basketItem);
        basketService.calcBasketTotalShipmentPrice(createBasketItemRequestDTO.BasketID(), basketItem);

        return basketItem;
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
