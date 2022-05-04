package com.example.productorderchain.dto.process.get;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductsResponseDTO (Long id,
                                      String name,
                                     BigDecimal price,
                                     BigDecimal discountRate,
                                     UUID barcode,
                                     String image,
                                     String brandName,
                                     String categoryName){
}
