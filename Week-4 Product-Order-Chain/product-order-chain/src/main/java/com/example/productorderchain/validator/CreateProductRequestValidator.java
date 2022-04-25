package com.example.productorderchain.validator;

import com.example.productorderchain.dto.process.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateProductRequestValidator implements Validator<CreateProductRequestDTO> {

    @Override
    public void validate(CreateProductRequestDTO productDTO) throws BaseValidationException {

        if (Objects.isNull(productDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer can not be null or empty");
        }
        if (!(StringUtils.hasLength(productDTO.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Product name can not be null or empty");
        }
        if (Objects.isNull(productDTO.price())) {
            throw new ValidationOperationException.CustomerNotValidException("Price can not be null or empty");
        }
        if (Objects.isNull(productDTO.barcode())) {
            throw new ValidationOperationException.CustomerNotValidException("Barcode number can not be null or empty");
        }
        if (Objects.isNull(productDTO.image())) {
            throw new ValidationOperationException.CustomerNotValidException("Image field can not be null or empty");
        }
        if (Objects.isNull(productDTO.brand())) {
            throw new ValidationOperationException.CustomerNotValidException("Brand can not be null or empty");
        }
        if (Objects.isNull(productDTO.category())) {
            throw new ValidationOperationException.CustomerNotValidException("Category can not be null or empty");
        }}


}
