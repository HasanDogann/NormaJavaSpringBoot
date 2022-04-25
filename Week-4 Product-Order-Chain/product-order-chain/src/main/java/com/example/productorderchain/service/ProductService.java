package com.example.productorderchain.service;


import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface ProductService {

    void createProduct(CreateProductRequestDTO productDTO);

    CreateProductRequestDTO getProduct(Long id) throws BaseException;

    Collection<GetProductsResponseDTO> getAllProducts();

    void deleteProduct(Long id, boolean hardDelete) throws BaseException;
}
