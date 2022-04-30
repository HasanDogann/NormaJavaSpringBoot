package com.example.productorderchain.service;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.dto.process.GetCategoriesResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface CategoryService {
    Result createCategory(CreateCategoryRequestDTO customerDTO);

    GetCategoriesResponseDTO getCategory(Long id) throws BaseException;

    SuccessDataResult<Collection<GetCategoriesResponseDTO>> getAllCategories();

    Result deleteCategory(Long id, boolean hardDelete) throws BaseException;


}
