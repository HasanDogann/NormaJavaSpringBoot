package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.model.Brand;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class BrandConverterImpl implements BrandConverter {
    @Override
    public Brand toBrand(CreateBrandRequestDTO createBrandRequestDTO) {
        Brand brand = new Brand();
        brand.setName(createBrandRequestDTO.name());
        brand.setBrandCountry(createBrandRequestDTO.brandCountry());

        return brand;
    }
    public Brand toBrandfromResponse(GetBrandsResponseDTO getBrandsResponseDTO) {
        Brand brand = new Brand();
        brand.setName(getBrandsResponseDTO.name());
        brand.setBrandCountry(getBrandsResponseDTO.brandCountry());

        return brand;
    }

    @Override
    public CreateBrandRequestDTO toCreateBrandRequest(Brand brand) {

        return new CreateBrandRequestDTO(brand.getName(), brand.getBrandCountry());
    }

    @Override
    public GetBrandsResponseDTO toGetBrandsResponse(Brand brand) {

        return new GetBrandsResponseDTO(brand.getName(), brand.getBrandCountry());
    }
}
