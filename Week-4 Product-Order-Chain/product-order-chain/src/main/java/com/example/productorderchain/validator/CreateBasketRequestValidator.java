package com.example.productorderchain.validator;


import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CreateBasketRequestValidator implements Validator<CreateBasketRequestDTO> {

    @Override
    public void validate(CreateBasketRequestDTO createBasketRequestDTO) throws BaseValidationException {

        if (Objects.isNull(createBasketRequestDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("BasketItem can not be null or empty");
        }

        if (Objects.isNull(createBasketRequestDTO.basketID())) {
            throw new ValidationOperationException.CustomerNotValidException("BasketId can not be null or empty");


    }


}}
