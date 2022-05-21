package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.model.dto.model.AccountOptionsDTO;
import com.example.bankingsystem.model.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceType;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
@Slf4j
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;


    @BeforeEach
    void setUp() throws Exception {
        Customer customer = new Customer();
        customer.setName("Customer Test name");
        customer.setSurname("Customer test surname");
        customer.setId(100L);
        customer.setEMail("abc@hotmail.com");
        customer.setPhone("155155155");
        customer.setCustomerAddress(new CustomerAddress("TR", "Ankara", "06000", "Central", customer));


    }

    @Test
    void shouldAddCustomerSuccessfullyWithCustomerService() {

        Customer customer = new Customer();
        customer.setName("Customer Test name");
        customer.setSurname("Customer test surname");
        customer.setId(100L);
        customer.setEMail("abc@hotmail.com");
        customer.setPhone("155155155");
        customer.setCustomerAddress(new CustomerAddress("TR", "Ankara", "06000", "Central", customer));

        customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName()
                , customer.getSurname(), customer.getEMail(), customer.getPhone(),
                new AccountOptionsDTO(AccountType.DEPOT,BalanceType.DOLLAR),
                new CustomerAddressDTO(customer.getCustomerAddress().getCountry(),
                        customer.getCustomerAddress().getCity(), customer.getCustomerAddress().getPostalCode(),
                        customer.getCustomerAddress().getDescription())));

        Assertions.assertNotNull(customerRepository.getById(customer.getId()));
    }

    @Test
    void shouldThrowCustomerNotFoundException() {
        Customer customer = new Customer();
        customer.setName("Customer Test name");
        customer.setSurname("Customer test surname");
        customer.setId(100L);
        customer.setEMail("abc@hotmail.com");
        customer.setPhone("155155155");
        customer.setCustomerAddress(new CustomerAddress("TR", "Ankara", "06000", "Central", customer));


        customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName()
                , customer.getSurname(), customer.getEMail(), customer.getPhone(),
                new AccountOptionsDTO(AccountType.DEPOT,BalanceType.DOLLAR),
                new CustomerAddressDTO(customer.getCustomerAddress().getCountry(),
                        customer.getCustomerAddress().getCity(), customer.getCustomerAddress().getPostalCode(),
                        customer.getCustomerAddress().getDescription())));

        Assertions.assertThrows(ServiceOperationNotFoundException.CustomerNotFoundException.class, () -> {
            customerService.getCustomer(5L);
        });

    }

    @Test
    void shouldThrowAlreadyDeletedException() {

        Customer customer = new Customer();
        customer.setName("Customer Test name");
        customer.setSurname("Customer test surname");
        customer.setId(100L);
        customer.setEMail("abc@hotmail.com");
        customer.setPhone("155155155");
        customer.setCustomerAddress(new CustomerAddress("TR", "Ankara", "06000", "Central", customer));



        customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName()
                , customer.getSurname(), customer.getEMail(), customer.getPhone(),
                new AccountOptionsDTO(AccountType.DEPOT,BalanceType.DOLLAR),
                new CustomerAddressDTO(customer.getCustomerAddress().getCountry(),
                        customer.getCustomerAddress().getCity(), customer.getCustomerAddress().getPostalCode(),
                        customer.getCustomerAddress().getDescription())));

        Assertions.assertNotNull(customerRepository.getById(customer.getId()));


        customer.setDeleted(true);
        customerRepository.save(customer);
        Assertions.assertNotNull(customerRepository.getById(customer.getId()));
        //repo ya gidip find  yapınca bir test başarılı bitiyor
        Assertions.assertDoesNotThrow(()->customerRepository.findById(customer.getId()));

        //Ama serviste aynı şekilde repoya gidip find yapınca; already deleted exception fırlatması gerekirken Not found exception fırlatıyor
        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException.class, () -> {
            customerService.getCustomer(customer.getId());
        });

    }


    @Test
    void should_not_delete_customer_when_accountBalance_is_more_than_zero(){

        Customer customer = new Customer();
        customer.setName("Customer Test name");
        customer.setSurname("Customer test surname");
        customer.setId(100L);
        customer.setEMail("abc@hotmail.com");
        customer.setPhone("155155155");
        Account account= accountService.getAccount(18L);
       Assertions.assertNotNull(account);
       account.setBalance(BigDecimal.valueOf(6000));
       accountRepository.save(account);
       Assertions.assertTrue(account.getBalance().compareTo(BigDecimal.valueOf(1000))>0);

        customer.setAccountList(Set.of(accountService.getAccount(account.getId())));


        customer.setCustomerAddress(new CustomerAddress("TR", "Ankara", "06000", "Central", customer));


        customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName()
                , customer.getSurname(), customer.getEMail(), customer.getPhone(),
                new AccountOptionsDTO(AccountType.DEPOT,BalanceType.DOLLAR),
                new CustomerAddressDTO(customer.getCustomerAddress().getCountry(),
                        customer.getCustomerAddress().getCity(), customer.getCustomerAddress().getPostalCode(),
                        customer.getCustomerAddress().getDescription())));

        Assertions.assertNotNull(customerRepository.getById(customer.getId()));



        Assertions.assertThrows(ServiceOperationCanNotDeleteException.CustomerBalanceNotZero.class,()->
        {
            customerService.deleteCustomer(customer.getId(),false);
        });


    }

}

