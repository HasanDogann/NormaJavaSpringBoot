package com.example.productorderchain.validator.ModelDTOValidation;

import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import com.example.productorderchain.validator.Validator;
import org.springframework.stereotype.Component;

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
