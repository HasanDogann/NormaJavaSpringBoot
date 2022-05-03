package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.get.GetBrandsResponseDTO;
import com.example.productorderchain.model.Brand;

public interface BrandConverter {
    Brand toBrand(CreateBrandRequestDTO createBrandRequestDTO);

    Brand toBrandfromResponse(GetBrandsResponseDTO getBrandsResponseDTO);

    CreateBrandRequestDTO toCreateBrandRequest(Brand brand);



    GetBrandsResponseDTO toGetBrandsResponse(Brand brand);
}
