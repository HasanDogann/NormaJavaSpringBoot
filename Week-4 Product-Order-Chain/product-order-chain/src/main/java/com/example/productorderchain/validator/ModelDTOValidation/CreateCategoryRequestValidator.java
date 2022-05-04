package com.example.productorderchain.validator.ModelDTOValidation;

import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.exception.BaseValidationException;
import com.example.productorderchain.exception.ValidationOperationException;
import com.example.productorderchain.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCategoryRequestValidator implements Validator<CreateCategoryRequestDTO> {

    @Override
    public void validate(CreateCategoryRequestDTO createCategoryRequestDTO) throws BaseValidationException {

        if (Objects.isNull(createCategoryRequestDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("Category can not be null or empty");
        }
        if (!(StringUtils.hasLength(createCategoryRequestDTO.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Category name can not be null or empty");
        }
        if (Objects.isNull(createCategoryRequestDTO.categoryField())) {
            throw new ValidationOperationException.CustomerNotValidException("Category Field  can not be null or empty");

        }}


}