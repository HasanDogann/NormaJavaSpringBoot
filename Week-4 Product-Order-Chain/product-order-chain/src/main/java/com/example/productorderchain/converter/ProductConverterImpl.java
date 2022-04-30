package com.example.productorderchain.converter;

import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.model.BrandDTO;
import com.example.productorderchain.dto.model.CategoryDTO;
import com.example.productorderchain.dto.process.*;
import com.example.productorderchain.model.*;
import com.example.productorderchain.service.BrandService;
import com.example.productorderchain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductConverterImpl implements ProductConverter {
    private  final BrandService brandService;
    private final BrandConverter brandConverter;
    private  final CategoryConverter categoryConverter;
    private final CategoryService categoryService;
    @Override
    public Product toProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = new Product();
        product.setName(createProductRequestDTO.name());
        product.setPrice(createProductRequestDTO.price());
        product.setBarcode(createProductRequestDTO.barcode());
        product.setImage(createProductRequestDTO.image());

        product.setBrand(brandConverter.toBrandfromResponse(brandService.getBrand(createProductRequestDTO.brandID())));
        product.setCategory(categoryConverter.toCategoryfromResponse(categoryService.getCategory(createProductRequestDTO.categoryID())));

        return product;
    }

    @Override
    public CreateProductRequestDTO toCreateProductRequest(Product product) {

        return new CreateProductRequestDTO(product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand().getId(),
                product.getCategory().getId(),
                product.getDiscountRate());


    }

    @Override
    public GetProductsResponseDTO toGetProductsResponse(Product product) {
        return new GetProductsResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountRate(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand().getName(),
                product.getCategory().getName());

    }


    private BrandDTO convertBrandDto(Brand brand) {

        return new BrandDTO(brand.getName(),brand.getBrandCountry());
    }

    private CategoryDTO convertCategoryDto(Category category) {
        return new CategoryDTO(category.getName(), category.getCategoryField());
    }

}
