package com.example.productorderchain.service.abstracts;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.get.GetCategoriesResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface CategoryService {
    Result createCategory(CreateCategoryRequestDTO customerDTO);

    GetCategoriesResponseDTO getCategory(Long id) throws BaseException;

    SuccessDataResult<Collection<GetCategoriesResponseDTO>> getAllCategories();

    Result deleteCategory(Long id, boolean hardDelete) throws BaseException;


}
