package com.example.productorderchain.converter;

import com.example.productorderchain.dto.model.BasketItemDTO;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketItemConverterImpl implements BasketItemConverter {
    private final ProductService productService;
    private final ProductConverter productConverter;



    @Override
    public BasketItem toBasketItem(CreateBasketItemRequestDTO basketItemDTO) {
        BasketItem basketItem = new BasketItem();
        basketItem.setQuantity(basketItemDTO.quantity());
        basketItem.setPrice(basketItemDTO.price());
        basketItem.setDiscountPrice(basketItemDTO.discountPrice());
        basketItem.setTaxPrice(basketItemDTO.taxPrice());
        basketItem.setShippingPrice(basketItemDTO.shippingPrice());
        basketItem.setProduct(basketItem.getProduct());
        basketItem.setBasket(basketItem.getBasket());
        return basketItem;
    }

    @Override
    public CreateBasketItemRequestDTO toCreateBasketItemRequest(BasketItem basketItem) {

        return new CreateBasketItemRequestDTO(basketItem.getQuantity(),
                basketItem.getPrice(),
                basketItem.getDiscountPrice(),
                basketItem.getTaxPrice(),
                basketItem.getShippingPrice(),
                basketItem.getProduct().getId(),
                basketItem.getBasket().getId());

    }

    @Override
    public GetBasketItemResponseDTO toGetBasketItemResponse(BasketItem basketItem) {

        return new GetBasketItemResponseDTO(basketItem.getQuantity(),
                basketItem.getPrice(),
                basketItem.getDiscountPrice(),
                basketItem.getShippingPrice(),
                basketItem.getProduct().getName());

    }
}
