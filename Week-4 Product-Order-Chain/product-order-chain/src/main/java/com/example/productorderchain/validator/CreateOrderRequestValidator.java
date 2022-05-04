package com.example.productorderchain.validator;

import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CreateOrderRequestValidator implements Validator<CreateOrderRequestDTO>{


    public void validate(CreateOrderRequestDTO createOrderRequestDTO) throws BaseValidationException {

        if (Objects.isNull(createOrderRequestDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("Order can not be null or empty");
        }
        if (Objects.isNull(createOrderRequestDTO.basketID())) {
            throw new ValidationOperationException.CustomerNotValidException("Order of basket id can not be null or empty");
        }
        if (Objects.isNull(createOrderRequestDTO.customerId())) {
            throw new ValidationOperationException.CustomerNotValidException("Order of Customer id can not be null or empty");
        }
        if (Objects.isNull(createOrderRequestDTO.paymentMethod())) {
            throw new ValidationOperationException.CustomerNotValidException("Order of payment method can not be null or empty");
        }


    }
}
