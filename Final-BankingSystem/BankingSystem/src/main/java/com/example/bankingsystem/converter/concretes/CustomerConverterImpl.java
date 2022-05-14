package com.example.bankingsystem.converter.concretes;

import com.example.bankingsystem.converter.abstracts.CustomerConverter;
import com.example.bankingsystem.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.entity.CustomerAddress;
import com.example.bankingsystem.entity.enums.AccountType;
import com.example.bankingsystem.entity.enums.BalanceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerConverterImpl implements CustomerConverter {


    @Override
    public Customer toCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer= new Customer();
        customer.setName(customerCreateRequestDTO.customerName());
        customer.setSurname(customerCreateRequestDTO.customerSurname());
        customer.setEmail(customerCreateRequestDTO.customerEmail());
        customer.setPhone(customerCreateRequestDTO.customerPhone());

        //Taking customer address when creating customer
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(customerCreateRequestDTO.customerAddress().country());
        customerAddress.setCity(customerCreateRequestDTO.customerAddress().city());
        customerAddress.setPostalCode(customerCreateRequestDTO.customerAddress().postalCode());
        customerAddress.setDescription(customerCreateRequestDTO.customerAddress().description());
        customer.setCustomerAddress(customerAddress);

        //When creating new Customer  we implement it with a base term deposit account
        Account account=new Account();
        Long a =new Random().nextLong(1000_000_00,9999_999_99);
        account.setAccountNumber(a);
        account.setIBAN("TR"+new BigDecimal(new Random().nextLong(1000_000_00,9999_999_99))+a);
        account.setAccountType(AccountType.TERM_DEPOSIT);
        account.setBalance(BigDecimal.ZERO);
        account.setBalanceType(BalanceType.TL);
        account.setCustomer(customer);
        Set<Account> accountsSet= new HashSet<Account>();
        accountsSet.add(account);

        //Adding  base account to Customer
        customer.setAccountList(accountsSet);

        return customer;
    }

    @Override
    public CustomerGetResponseDTO toCustomerResponse(Customer customer) {

        return new CustomerGetResponseDTO(customer.getName(), customer.getSurname(),customer.getId(), customer.getEmail(), customer.getPhone(), customer.getAccountList(),customer.getCardList());
    }
}
