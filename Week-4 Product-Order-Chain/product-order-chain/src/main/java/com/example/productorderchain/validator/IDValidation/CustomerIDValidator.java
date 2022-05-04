package com.example.productorderchain.validator.IDValidation;

import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import com.example.productorderchain.validator.Validator;
import org.springframework.stereotype.Component;

@Component("customerIDQ")
public class CustomerIDValidator implements Validator<Long> {
    @Override
    public void validate(Long id) throws BaseValidationException {
        if (id < 0) {
            throw new ValidationOperationException.CustomerIDNotValidException("Customer ID should be greater than zero.");
        }
    }
}
