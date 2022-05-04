package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BasketConverter;
import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BasketConverterImpl implements BasketConverter {


    @Override
    public Basket toBasket(CreateBasketRequestDTO createBasketRequestDTO) {
        Basket basket = new Basket();


        basket.setTotalPrice(BigDecimal.ZERO);
        basket.setDiscountPrice(BigDecimal.ZERO);
        basket.setTaxPrice(BigDecimal.ZERO);
        basket.setShippingPrice(BigDecimal.ZERO);

        return basket;
    }


    @Override
    public GetBasketResponseDTO toGetBasketResponseDTO(Basket basket) {

        return new GetBasketResponseDTO(basket.getId(),basket.getItems());
    }
}
