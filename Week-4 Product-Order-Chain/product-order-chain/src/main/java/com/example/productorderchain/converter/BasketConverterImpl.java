package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BasketConverterImpl implements BasketConverter {
    @Override
    public Basket toBasket(CreateBasketRequestDTO createBasketRequestDTO) {
        Basket basket = new Basket();
        BigDecimal totalPrice = createBasketRequestDTO.basketItemSet().stream().map(BasketItem::totalBasketItemPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalPrice);
        BigDecimal totalPriceDiscount = createBasketRequestDTO.basketItemSet().stream().map(BasketItem::totalBasketItemPriceDiscount).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setDiscountPrice(totalPriceDiscount);
        basket.setItems(createBasketRequestDTO.basketItemSet());
        BigDecimal totalTaxPrice = createBasketRequestDTO.basketItemSet().stream().map(BasketItem::totalBasketItemTaxPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalTaxPrice);
        BigDecimal totalShipPrice = createBasketRequestDTO.basketItemSet().stream().map(BasketItem::totalBasketItemShippingPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalShipPrice);

        return basket;
    }

    @Override
    public CreateBasketRequestDTO toCreateBasketRequestDTO(Basket basket) {

        return new CreateBasketRequestDTO(basket.getItems(),basket.getId());
    }

    @Override
    public GetBasketResponseDTO toGetBasketResponseDTO(Basket basket) {

        return new GetBasketResponseDTO(basket.getItems());
    }
}
