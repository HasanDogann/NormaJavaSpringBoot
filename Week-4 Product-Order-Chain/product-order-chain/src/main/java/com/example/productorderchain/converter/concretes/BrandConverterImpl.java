package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.BrandConverter;
import com.example.productorderchain.dto.process.create.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.get.GetBrandsResponseDTO;
import com.example.productorderchain.model.Brand;
import org.springframework.stereotype.Component;

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
    public GetBrandsResponseDTO toGetBrandsResponse(Brand brand) {

        return new GetBrandsResponseDTO(brand.getName(), brand.getBrandCountry());
    }
}
