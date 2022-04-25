package com.example.productorderchain.converter;

import com.example.productorderchain.dto.model.BrandDTO;
import com.example.productorderchain.dto.model.CategoryDTO;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.model.*;
import org.springframework.stereotype.Component;


@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product toProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = new Product();
        product.setName(createProductRequestDTO.name());
        product.setPrice(createProductRequestDTO.price());
        product.setBarcode(createProductRequestDTO.barcode());
        product.setImage(createProductRequestDTO.image());

        Brand brand = new Brand();
        brand.setName(createProductRequestDTO.brand().name());
        brand.setBrandCountry(createProductRequestDTO.brand().brandCountry());
        product.setBrand(brand);

        Category category = new Category();
        category.setName(createProductRequestDTO.category().name());
        product.setCategory(category);

        return product;
    }

    @Override
    public CreateProductRequestDTO toCreateProductRequest(Product product) {

        return new CreateProductRequestDTO(product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                convertBrandDto(product.getBrand()),
                convertCategoryDto(product.getCategory()));


    }

    @Override
    public GetProductsResponseDTO toGetProductsResponse(Product product) {
        return new GetProductsResponseDTO(product.getId(),product.getName(), product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                convertBrandDto(product.getBrand()),
                convertCategoryDto(product.getCategory()));
    }


    private BrandDTO convertBrandDto(Brand brand) {
        return new BrandDTO(brand.getName(),brand.getBrandCountry());
    }
    private CategoryDTO convertCategoryDto(Category category) {
        return new CategoryDTO(category.getName());
    }

}
