package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BrandConverter;
import com.example.productorderchain.converter.abstracts.CategoryConverter;
import com.example.productorderchain.converter.abstracts.ProductConverter;
import com.example.productorderchain.dto.process.create.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.get.GetProductsResponseDTO;
import com.example.productorderchain.model.*;
import com.example.productorderchain.service.abstracts.BrandService;
import com.example.productorderchain.service.abstracts.CategoryService;
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


}
