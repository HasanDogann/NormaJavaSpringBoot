package com.example.productorderchain.dto.process.get;

import com.example.productorderchain.dto.model.CustomerAddressDTO;
import com.example.productorderchain.model.Gender;

import java.math.BigDecimal;

public record GetCustomersResponseDTO(Long id,
                                      String username,
                                      String email,
                                      Gender gender,
                                      BigDecimal customerCoupon,
                                      CustomerAddressDTO customerAddressDTO) {
}
