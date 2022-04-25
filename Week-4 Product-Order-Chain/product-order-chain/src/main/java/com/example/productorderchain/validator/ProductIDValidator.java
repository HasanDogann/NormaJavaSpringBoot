package com.example.productorderchain.validator;

import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

@Component("productIDQ")
public class ProductIDValidator implements Validator<Long> {
    @Override
    public void validate(Long id) throws BaseValidationException {
        if (id < 0) {
            throw new ValidationOperationException.ProductIDNotValidException("Product ID should be greater than zero.");
        }
    }
}
