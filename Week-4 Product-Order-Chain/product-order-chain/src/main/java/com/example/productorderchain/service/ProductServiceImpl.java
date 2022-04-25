package com.example.productorderchain.service;

import com.example.productorderchain.converter.ProductConverter;
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
    public void createProduct(CreateProductRequestDTO productDTO) {
    Product product = productConverter.toProduct(productDTO);
    productRepository.save(product);

    }

    @Override
    public CreateProductRequestDTO getProduct(Long id) throws BaseException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Product not found"));
        return productConverter.toCreateProductRequest(product);
    }

    @Override
    public Collection<GetProductsResponseDTO> getAllProducts() {

        return productRepository
                .findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(productConverter::toGetProductsResponse)
                .toList();


    }

    @Override
    public void deleteProduct(Long id, boolean hardDelete) throws BaseException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product is not found"));
        if (product.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Product is already deleted");
        }
        if (hardDelete) {
            productRepository.delete(product);
            return;
        }
        product.setDeleted(true);
        productRepository.save(product);
    }

    }
