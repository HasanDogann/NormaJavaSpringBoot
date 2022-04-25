package com.example.productorderchain.dto.model;

public record CustomerAddressDTO(String phoneNumber,
                                 String country,
                                 String city,
                                 String postalCode,
                                 String description) {
}
