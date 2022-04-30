package com.example.productorderchain.converter;

import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.model.Brand;

public interface BrandConverter {
    Brand toBrand(CreateBrandRequestDTO createBrandRequestDTO);

    Brand toBrandfromResponse(GetBrandsResponseDTO getBrandsResponseDTO);

    CreateBrandRequestDTO toCreateBrandRequest(Brand brand);



    GetBrandsResponseDTO toGetBrandsResponse(Brand brand);
}
