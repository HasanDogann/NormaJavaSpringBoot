package com.example.productorderchain.dto.process;

import com.example.productorderchain.dto.model.CustomerAddressDTO;
import com.example.productorderchain.model.Gender;

public record GetCustomersResponseDTO(Long id,
                                      String username,
                                      String email,
                                      Gender gender,
                                      CustomerAddressDTO customerAddressDTO) {
}
