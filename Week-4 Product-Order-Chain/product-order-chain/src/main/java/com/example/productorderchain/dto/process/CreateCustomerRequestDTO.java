package com.example.productorderchain.dto.process;

import com.example.productorderchain.dto.model.CustomerAddressDTO;
import com.example.productorderchain.model.Gender;

public record CreateCustomerRequestDTO(String userName, String email, Long identity, Gender gender, String password,
                                       CustomerAddressDTO customerAddress) {

}
