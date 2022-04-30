package com.example.productorderchain.service;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.exception.BaseException;

import java.util.Collection;

public interface BrandService {

    Result createBrand(CreateBrandRequestDTO customerDTO);

    GetBrandsResponseDTO getBrand(Long id) throws BaseException;

    SuccessDataResult<Collection<GetBrandsResponseDTO>> getAllBrands();

    Result deleteBrand(Long id, boolean hardDelete) throws BaseException;




}
