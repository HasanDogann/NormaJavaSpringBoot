package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserRegisterRequest;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerConverterImpl implements CustomerConverter {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer toCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = new Customer();
        customer.setName(customerCreateRequestDTO.customerName());
        customer.setSurname(customerCreateRequestDTO.customerSurname());
        customer.setEMail(customerCreateRequestDTO.customerEmail());
        customer.setPhone(customerCreateRequestDTO.customerPhone());

        //Taking customer address when creating customer
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(customerCreateRequestDTO.customerAddress().country());
        customerAddress.setCity(customerCreateRequestDTO.customerAddress().city());
        customerAddress.setPostalCode(customerCreateRequestDTO.customerAddress().postalCode());
        customerAddress.setDescription(customerCreateRequestDTO.customerAddress().description());
        customerAddress.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);

        //When you are creating new Customer you take the account type and balance type from customer
        Account account = new Account();
        account.setAccountType(customerCreateRequestDTO.accountOptionsDTO().accountType());
        account.setBalanceType(customerCreateRequestDTO.accountOptionsDTO().balanceType());
        Long a = new Random().nextLong(1000_000_00, 9999_999_99);
        account.setAccountNumber(a);
        account.setIBAN("TR" + new BigDecimal(new Random().nextLong(1000_000_00, 9999_999_99)) + a);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        account.setCreationDate(formatter.format(date));
        account.setCustomer(customer);

        //adding account to Customer
        customer.addAccountToCustomer(Set.of(account));


        return customer;
    }

   /* @Override
    public Customer toCustomer(UserRegisterRequest userRegisterRequest) {
        Customer customer = new Customer();
        customer.setName(userRegisterRequest.customerCreateRequestDTO().customerName());
        customer.setSurname(userRegisterRequest.customerCreateRequestDTO().customerSurname());
        customer.setEMail(userRegisterRequest.email());
        customer.setPhone(userRegisterRequest.customerCreateRequestDTO().customerPhone());
        customer.setPassword(userRegisterRequest.password());
        log.info(customer.getPassword()+"converter step1");
        //Taking customer address when creating customer
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(userRegisterRequest.customerCreateRequestDTO().customerAddress().country());
        customerAddress.setCity(userRegisterRequest.customerCreateRequestDTO().customerAddress().city());
        customerAddress.setPostalCode(userRegisterRequest.customerCreateRequestDTO().customerAddress().postalCode());
        customerAddress.setDescription(userRegisterRequest.customerCreateRequestDTO().customerAddress().description());
        customer.setCustomerAddress(customerAddress);

        //When you are creating new Customer you take the account type and balance type from customer
        Account account = new Account();
        account.setAccountType(userRegisterRequest.customerCreateRequestDTO().accountOptionsDTO().accountType());
        account.setBalanceType(userRegisterRequest.customerCreateRequestDTO().accountOptionsDTO().balanceType());
        Long a = new Random().nextLong(1000_000_00, 9999_999_99);
        account.setAccountNumber(a);
        account.setIBAN("TR" + new BigDecimal(new Random().nextLong(1000_000_00, 9999_999_99)) + a);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        account.setCreationDate(formatter.format(date));
        account.setCustomer(customer);

        //adding account to Customer
        customer.addAccountToCustomer(Set.of(account));


        return customer;
    }*/

    @Override
    public CustomerGetResponseDTO toCustomerResponse(Customer customer) {

        return new CustomerGetResponseDTO(customer.getName(), customer.getSurname(), customer.getId(), customer.getEMail(), customer.getPhone(), customer.getAccountList(), customer.getCardList());
    }

    @Override
    public Customer toCustomerFromUpdateRequest(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = customerRepository.getById(customerUpdateRequestDTO.id());
        customer.setName(customerUpdateRequestDTO.customerName());
        customer.setSurname(customerUpdateRequestDTO.customerSurname());
        customer.setEMail(customerUpdateRequestDTO.customerEmail());
        customer.setPhone(customerUpdateRequestDTO.customerPhone());
        return customer;
    }
}
