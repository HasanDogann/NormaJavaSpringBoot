package com.example.productorderchain.controller;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.service.BrandService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Qualifier("brandIDQ")
    private final Validator<Long> brandIdValidator;


    private final Validator<CreateBrandRequestDTO> createBrandValidator;

    private final BrandService brandService;

    public BrandController(@Qualifier("brandIDQ") Validator<Long> brandIdValidator, Validator<CreateBrandRequestDTO> createBrandValidator, BrandService brandService) {
        this.brandIdValidator = brandIdValidator;
        this.createBrandValidator = createBrandValidator;
        this.brandService = brandService;
    }


    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody CreateBrandRequestDTO createBrandRequestDTO) {
        createBrandValidator.validate(createBrandRequestDTO);
        Result result= brandService.createBrand(createBrandRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetBrandsResponseDTO>>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<GetBrandsResponseDTO>> getBrand(@PathVariable Long id) {
        brandIdValidator.validate(id);
        return ResponseEntity.ok(new SuccessDataResult<>(brandService.getBrand(id),"Listed brand succesfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        brandIdValidator.validate(id);
        Result result = brandService.deleteBrand(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }



}
