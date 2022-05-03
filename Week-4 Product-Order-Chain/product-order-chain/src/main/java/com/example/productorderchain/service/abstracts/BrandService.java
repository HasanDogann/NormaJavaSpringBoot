package com.example.productorderchain.service.abstracts;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.get.GetBrandsResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface BrandService {

    Result createBrand(CreateBrandRequestDTO customerDTO);

    GetBrandsResponseDTO getBrand(Long id) throws BaseException;

    SuccessDataResult<Collection<GetBrandsResponseDTO>> getAllBrands();

    Result deleteBrand(Long id, boolean hardDelete) throws BaseException;




}
