package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.entity.CustomerAddress;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerConverterImpl implements CustomerConverter {

    private final CustomerRepository customerRepository;

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
        customer.setCustomerAddress(customerAddress);

        //When you are creating new Customer , implements it with a base term deposit account
        Account account = new Account();
        Long a = new Random().nextLong(1000_000_00, 9999_999_99);
        account.setAccountNumber(a);
        account.setIBAN("TR" + new BigDecimal(new Random().nextLong(1000_000_00, 9999_999_99)) + a);
        account.setAccountType(AccountType.TERM_DEPOSIT);
        account.setBalance(BigDecimal.ZERO);
        account.setBalanceType(BalanceType.TL);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        account.setCreationDate(formatter.format(date));
        account.setCustomer(customer);

        //adding account to Customer
        customer.addAccountToCustomer(Set.of(account));


        return customer;
    }

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
