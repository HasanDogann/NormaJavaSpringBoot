package com.example.productorderchain.converter.abstracts;


import com.example.productorderchain.dto.process.create.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.get.GetProductsResponseDTO;
import com.example.productorderchain.model.Product;

public interface ProductConverter {
    Product toProduct(CreateProductRequestDTO createProductRequestDTO);

    CreateProductRequestDTO toCreateProductRequest(Product product);

    GetProductsResponseDTO toGetProductsResponse(Product product);
}
