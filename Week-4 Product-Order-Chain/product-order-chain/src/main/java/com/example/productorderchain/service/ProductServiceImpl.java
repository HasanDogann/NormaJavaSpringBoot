package com.example.productorderchain.service;

import com.example.productorderchain.converter.ProductConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Product;
import com.example.productorderchain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductConverter productConverter;
    private final ProductRepository  productRepository;


    @Override
    public Result createProduct(CreateProductRequestDTO productDTO) {
    Product product = productConverter.toProduct(productDTO);
    productRepository.save(product);
    return new SuccessResult("Product "+product.getName()+" is added successfuly");
    }

    @Override
    public SuccessDataResult<CreateProductRequestDTO> getProduct(Long id) throws BaseException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Product not found"));
        if (product.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Product was deleted");
        }
        return new SuccessDataResult<>( productConverter.toCreateProductRequest(product),"Product is listed successfully");
    }

    @Override
    public SuccessDataResult<Collection<GetProductsResponseDTO>> getAllProducts() {

        return new SuccessDataResult<>( productRepository
                .findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(productConverter::toGetProductsResponse)
                .toList(),"Products are listed successfully");


    }

    @Override
    public Result deleteProduct(Long id, boolean hardDelete) throws BaseException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product is not found"));
        if (product.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Product is already deleted");
        }
        if (hardDelete) {
            productRepository.delete(product);
            return new SuccessResult("Product "+product.getName()+" is deleted with HardDelete successfully");
        }
        product.setDeleted(true);
        productRepository.save(product);
        return new SuccessResult("Product "+product.getName()+" is deleted with SoftDelete successfully");
    }

    }
