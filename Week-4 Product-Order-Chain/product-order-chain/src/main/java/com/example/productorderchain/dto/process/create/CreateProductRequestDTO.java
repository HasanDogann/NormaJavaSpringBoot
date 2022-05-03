package com.example.productorderchain.dto.process.create;


import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequestDTO(String name,
                BigDecimal price,
                UUID barcode,
                String image,
                                      Long brandID,
                                      Long categoryID,
                                      BigDecimal discountRate
                                      ) {

}
