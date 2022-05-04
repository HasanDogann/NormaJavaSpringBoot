package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.CategoryConverter;
import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.get.GetCategoriesResponseDTO;
import com.example.productorderchain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category  = new Category();
        category.setName(createCategoryRequestDTO.name());
        category.setCategoryField(createCategoryRequestDTO.categoryField());

        return category;
    }

    @Override
    public Category toCategoryfromResponse(GetCategoriesResponseDTO getCategoriesResponseDTO) {
        Category category = new Category();
        category.setName(getCategoriesResponseDTO.name());
        category.setCategoryField(getCategoriesResponseDTO.categoryField());

        return category;
    }

    @Override
    public GetCategoriesResponseDTO toGetCategoriesResponse(Category category) {
        return new GetCategoriesResponseDTO(category.getName(), category.getCategoryField());
    }
}
