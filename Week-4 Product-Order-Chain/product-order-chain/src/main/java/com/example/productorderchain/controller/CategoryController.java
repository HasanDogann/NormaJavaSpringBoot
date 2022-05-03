package com.example.productorderchain.controller;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.get.GetCategoriesResponseDTO;
import com.example.productorderchain.service.abstracts.CategoryService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Qualifier("categoryIDQ")
    private final Validator<Long> categoryIdValidator;


    private final Validator<CreateCategoryRequestDTO> createCategoryValidator;

    private final CategoryService categoryService;

    public CategoryController(@Qualifier("categoryIDQ") Validator<Long> categoryIdValidator, Validator<CreateCategoryRequestDTO> createCategoryValidator, CategoryService categoryService) {
        this.categoryIdValidator = categoryIdValidator;
        this.createCategoryValidator = createCategoryValidator;
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        createCategoryValidator.validate(createCategoryRequestDTO);
        Result result= categoryService.createCategory(createCategoryRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetCategoriesResponseDTO>>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<GetCategoriesResponseDTO>> getCategory(@PathVariable Long id) {
        categoryIdValidator.validate(id);
        return ResponseEntity.ok(new SuccessDataResult<>(categoryService.getCategory(id),"Listed brand succesfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id,
                                         @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        categoryIdValidator.validate(id);
        Result result =categoryService.deleteCategory(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }

}
