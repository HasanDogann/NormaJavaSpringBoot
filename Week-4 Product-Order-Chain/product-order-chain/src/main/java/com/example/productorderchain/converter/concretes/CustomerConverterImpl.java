package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.CustomerConverter;
import com.example.productorderchain.dto.process.create.CreateCustomerRequestDTO;
import com.example.productorderchain.dto.model.CustomerAddressDTO;
import com.example.productorderchain.dto.process.get.GetCustomersResponseDTO;
import com.example.productorderchain.model.Customer;
import com.example.productorderchain.model.CustomerAddress;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public
class CustomerConverterImpl implements CustomerConverter {

    @Override
    public Customer toCustomer(CreateCustomerRequestDTO customerDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerDTO.userName());
        customer.setEmail(customerDTO.email());
        customer.setIdentity(customerDTO.identity());
        customer.setGender(customerDTO.gender());
        customer.setPassword(customerDTO.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("Hasan DOĞAN");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(customerDTO.customerAddress().phoneNumber());
        customerAddress.setCountry(customerDTO.customerAddress().country());
        customerAddress.setCity(customerDTO.customerAddress().city());
        customerAddress.setPostalCode(customerDTO.customerAddress().postalCode());
        customerAddress.setDescription(customerDTO.customerAddress().description());

        customerAddress.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);

        return customer;
    }


    @Override
    public GetCustomersResponseDTO toGetCustomersResponse(Customer customer) {
        return new GetCustomersResponseDTO(customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getGender(),
                customer.getDiscountCoupon(),
                convertCustomerAddressDto(customer.getCustomerAddress()));

    }

    private CustomerAddressDTO convertCustomerAddressDto(CustomerAddress customerAddress) {
        return new CustomerAddressDTO(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }
}
