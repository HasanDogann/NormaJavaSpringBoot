package com.example.productorderchain.validator;

import com.example.productorderchain.exception.BaseValidationException;

public interface Validator<T> {

    void validate(T input) throws BaseValidationException;
}
