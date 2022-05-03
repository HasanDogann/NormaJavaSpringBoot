package com.example.productorderchain.validator;

import com.example.productorderchain.dto.process.create.CreateBrandRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateBrandRequestValidator implements Validator<CreateBrandRequestDTO> {

    @Override
    public void validate(CreateBrandRequestDTO createBrandRequestDTO) throws BaseValidationException {

        if (Objects.isNull(createBrandRequestDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("Brand can not be null or empty");
        }
        if (!(StringUtils.hasLength(createBrandRequestDTO.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Brand name can not be null or empty");
        }
        if (Objects.isNull(createBrandRequestDTO.brandCountry())) {
            throw new ValidationOperationException.CustomerNotValidException("Brand Country can not be null or empty");

        }}


}
