package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.get.GetCategoriesResponseDTO;
import com.example.productorderchain.model.Category;

public interface CategoryConverter {
    Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

    Category toCategoryfromResponse(GetCategoriesResponseDTO getCategoriesResponseDTO);

    CreateCategoryRequestDTO toCreateCategoryRequest(Category category);

    GetCategoriesResponseDTO toGetCategoriesResponse(Category category);
}
