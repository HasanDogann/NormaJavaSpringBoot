package com.example.bankingsystem.converter.impl;

import com.example.bankingsystem.converter.CustomerConverter;
import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.dto.response.CustomerGetResponseDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

        //Customer's account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
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
