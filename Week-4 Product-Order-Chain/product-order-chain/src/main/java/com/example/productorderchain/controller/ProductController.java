package com.example.productorderchain.controller;

import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.service.ProductService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("api/products")
public class ProductController {

    @Qualifier("productReqQ")
    private final Validator<CreateProductRequestDTO> createProductValidator;

    @Qualifier("productIDQ")
    private final Validator<Long> productIdValidator;
    private final ProductService productService;

    public ProductController(Validator<CreateProductRequestDTO> createProductValidator, @Qualifier("productIDQ") Validator<Long> productIdValidator, ProductService productService) {
        this.createProductValidator = createProductValidator;
        this.productIdValidator = productIdValidator;
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        createProductValidator.validate(createProductRequestDTO);
        productService.createProduct(createProductRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<GetProductsResponseDTO>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProductRequestDTO> getProduct(@PathVariable Long id) {
        productIdValidator.validate(id);
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        productIdValidator.validate(id);
        productService.deleteProduct(id,hardDelete);
        return ResponseEntity.ok().build();
    }



}
