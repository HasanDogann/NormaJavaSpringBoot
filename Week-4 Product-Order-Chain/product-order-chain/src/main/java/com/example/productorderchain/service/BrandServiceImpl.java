package com.example.productorderchain.service;

import com.example.productorderchain.converter.BrandConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.CreateBrandRequestDTO;
import com.example.productorderchain.dto.process.GetBrandsResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Brand;
import com.example.productorderchain.model.Product;
import com.example.productorderchain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandConverter brandConverter;

    @Override
    public Result createBrand(CreateBrandRequestDTO createBrandRequestDTO) {
        Brand brand = brandConverter.toBrand(createBrandRequestDTO);
        brandRepository.save(brand);
        return new SuccessResult("Brand "+brand.getName()+" is added successfuly");

    }

    @Override
    public GetBrandsResponseDTO getBrand(Long id) throws BaseException {
        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Brand not found"));
        if (brand.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Brand was deleted");
        }
        return brandConverter.toGetBrandsResponse(brand);
    }

    @Override
    public SuccessDataResult<Collection<GetBrandsResponseDTO>> getAllBrands() {
        return new SuccessDataResult<>( brandRepository.findAllBrandsByDeleteStatusByJPQL(false).stream().map(brandConverter::toGetBrandsResponse).toList()
                ,"All brands are listed successfully");

    }

    @Override
    public Result deleteBrand(Long id, boolean hardDelete) throws BaseException {
       Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Brand is not found"));
        if (brand.isDeleted()) {
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Brand is already deleted");
        }
        if (hardDelete) {
            brandRepository.delete(brand);
            return new SuccessResult("Brand "+brand.getName()+" is deleted with HardDelete successfully");
        }
        brand.setDeleted(true);
        brandRepository.save(brand);
        return new SuccessResult("Brand "+brand.getName()+" is deleted with SoftDelete successfully");
    }

}
