package com.example.productorderchain.converter;


import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.model.Product;

public interface ProductConverter {
    Product toProduct(CreateProductRequestDTO createProductRequestDTO);

    CreateProductRequestDTO toCreateProductRequest(Product product);

    GetProductsResponseDTO toGetProductsResponse(Product product);
}
