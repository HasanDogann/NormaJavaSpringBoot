package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.GetCategoriesResponseDTO;
import com.example.productorderchain.model.Category;

public interface CategoryConverter {
    Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

    Category toCategoryfromResponse(GetCategoriesResponseDTO getCategoriesResponseDTO);

    CreateCategoryRequestDTO toCreateCategoryRequest(Category category);

    GetCategoriesResponseDTO toGetCategoriesResponse(Category category);
}
