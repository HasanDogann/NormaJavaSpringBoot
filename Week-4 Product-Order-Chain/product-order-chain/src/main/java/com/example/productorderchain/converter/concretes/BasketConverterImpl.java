package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BasketConverter;
import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.service.abstracts.BasketItemService;
import com.example.productorderchain.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BasketConverterImpl implements BasketConverter {

    private final ProductService productService;

    @Override
    public Basket toBasket(CreateBasketRequestDTO createBasketRequestDTO) {
        Basket basket = new Basket();


        basket.setTotalPrice(BigDecimal.ZERO);
        basket.setDiscountPrice(BigDecimal.ZERO);
        basket.setTaxPrice(BigDecimal.ZERO);
        basket.setShippingPrice(BigDecimal.ZERO);
      /*  basket.setTotalPrice(basketItemService.getAllBasketItems().stream().map(BasketItem::totalBasketItemPrice));

        BigDecimal totalPrice = createBasketRequestDTO.basketItems().stream().map(BasketItem::totalBasketItemPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalPrice);
        BigDecimal totalPriceDiscount = createBasketRequestDTO.basketItems().stream().map(BasketItem::totalBasketItemPriceDiscount).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setDiscountPrice(totalPriceDiscount);
        basket.setItems(createBasketRequestDTO.basketItems());
        BigDecimal totalTaxPrice = createBasketRequestDTO.basketItems().stream().map(BasketItem::totalBasketItemTaxPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalTaxPrice);
        BigDecimal totalShipPrice = createBasketRequestDTO.basketItems().stream().map(BasketItem::totalBasketItemShippingPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        basket.setTotalPrice(totalShipPrice);
*/
        return basket;
    }

    @Override
    public CreateBasketRequestDTO toCreateBasketRequestDTO(Basket basket) {

        return new CreateBasketRequestDTO(basket.getId());
    }

    @Override
    public GetBasketResponseDTO toGetBasketResponseDTO(Basket basket) {

        return new GetBasketResponseDTO(basket.getId(),basket.getItems());
    }
}
