package com.example.productorderchain.service.concretes;

import com.example.productorderchain.converter.abstracts.CategoryConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.create.CreateCategoryRequestDTO;
import com.example.productorderchain.dto.process.get.GetCategoriesResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Category;
import com.example.productorderchain.repository.CategoryRepository;
import com.example.productorderchain.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public Result createCategory(CreateCategoryRequestDTO customerDTO) {

        Category category = categoryConverter.toCategory(customerDTO);
        categoryRepository.save(category);
        return new SuccessResult("Category "+category.getName()+" is added successfuly");

    }

    @Override
    public GetCategoriesResponseDTO getCategory(Long id) throws BaseException {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Category is not found"));
        if (category.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Category was deleted");
        }
        return categoryConverter.toGetCategoriesResponse(category);
    }

    @Override
    public SuccessDataResult<Collection<GetCategoriesResponseDTO>> getAllCategories() {

        return new SuccessDataResult<>( categoryRepository.findAllBrandsByDeleteStatusByJPQL(false).stream().map(categoryConverter::toGetCategoriesResponse).toList()
                ,"All categories are listed successfully");
    }

    @Override
    public Result deleteCategory(Long id, boolean hardDelete) throws BaseException {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Category is not found"));
        if (category.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Category is already deleted");
        }
        if (hardDelete) {
            categoryRepository.delete(category);
            return new SuccessResult("Category "+category.getName()+" is deleted with HardDelete successfully");
        }
        category.setDeleted(true);
        categoryRepository.save(category);
        return new SuccessResult("Category "+category.getName()+" is deleted with SoftDelete successfully");
    }

}

