package com.example.productorderchain.service;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface ProductService {

    Result createProduct(CreateProductRequestDTO productDTO);

    SuccessDataResult<GetProductsResponseDTO> getProduct(Long id) throws BaseException;

    SuccessDataResult<Collection<GetProductsResponseDTO>> getAllProducts();

    Result deleteProduct(Long id, boolean hardDelete) throws BaseException;
}
