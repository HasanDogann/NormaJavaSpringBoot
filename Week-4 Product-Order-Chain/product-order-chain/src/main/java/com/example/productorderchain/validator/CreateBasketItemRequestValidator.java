package com.example.productorderchain.validator;

import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateBasketItemRequestValidator implements Validator<CreateBasketItemRequestDTO> {

    @Override
    public void validate(CreateBasketItemRequestDTO createBasketItemRequestDTO) throws BaseValidationException {

        if (Objects.isNull(createBasketItemRequestDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem can not be null or empty");
        }
        if (Objects.isNull(createBasketItemRequestDTO.quantity())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem quantity can not be null or empty");
        }
        if (Objects.isNull(createBasketItemRequestDTO.price())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem price can not be null or empty");

        }
        if (Objects.isNull(createBasketItemRequestDTO.discountPrice())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem disc. price can not be null or empty");
        }
        if (Objects.isNull(createBasketItemRequestDTO.taxPrice())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem tax price can not be null or empty");

        }
        if (Objects.isNull(createBasketItemRequestDTO.shippingPrice())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem ship. price can not be null or empty");
        }
        if (Objects.isNull(createBasketItemRequestDTO.productID())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem product id can not be null or empty");

        }
    }


}
