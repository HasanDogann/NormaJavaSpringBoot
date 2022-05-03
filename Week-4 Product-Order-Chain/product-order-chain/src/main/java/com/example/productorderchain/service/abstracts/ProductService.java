package com.example.productorderchain.service.abstracts;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.get.GetProductsResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.model.Product;

import java.util.Collection;

public interface ProductService {

    Result createProduct(CreateProductRequestDTO productDTO);

    Product getProduct(Long id) throws BaseException;

    SuccessDataResult<Collection<GetProductsResponseDTO>> getAllProducts();

    Result deleteProduct(Long id, boolean hardDelete) throws BaseException;
}
