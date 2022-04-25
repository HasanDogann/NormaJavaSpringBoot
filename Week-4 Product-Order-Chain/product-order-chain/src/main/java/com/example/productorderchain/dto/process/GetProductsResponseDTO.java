package com.example.productorderchain.dto.process;

import com.example.productorderchain.dto.model.BrandDTO;
import com.example.productorderchain.dto.model.CategoryDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductsResponseDTO (Long id,
                                      String name,
                                     BigDecimal price,
                                     UUID barcode,
                                     String image,
                                     BrandDTO brand,
                                     CategoryDTO category){
}
