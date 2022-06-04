package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.model.CustomerAddressDTO;
import com.example.bankingsystem.model.dto.request.CustomerCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CustomerUpdateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CustomerRepository;
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

    @BeforeEach
    void setUp() {

    }

   @Test
    void should_Add_Customer_Successfully()  {
       Customer customer = new Customer();
       customer.setName("Demo");
       customer.setSurname("Customer");
       customer.setMail("Demo3@mail");
       customer.setPhone("90123456");
       customer.setCustomerAddress(
               new CustomerAddress("TR","Ankara","06000","Capital City",customer));



        Assertions.assertDoesNotThrow(()-> customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName(),
                customer.getSurname(), customer.getMail(),
                customer.getPhone(),2000,
                new CustomerAddressDTO("TR","Ankara","06000","Capital City"))));
    }
    @Test
    void throws_Adding_Customer_Already_Created_Exception()  {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));



        Assertions.assertThrows(ServiceOperationCanNotCreateException.CustomerIsAlreadyCreatedException.class,
                ()-> customerService.addCustomer(new CustomerCreateRequestDTO(customer.getName(),
                customer.getSurname(), customer.getMail(),
                customer.getPhone(),2000,
                new CustomerAddressDTO("TR","Ankara","06000","Capital City"))));
    }
    @Test
    void deleting_Customer_Soft_And_Successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo03@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        customerRepository.save(customer);

        Assertions.assertDoesNotThrow(()->{
            customerService.deleteCustomer(customer.getId(),false);

        });

    }
    @Test
    void deleting_Customer_Hard_And_Successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo04@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        customerRepository.save(customer);

        Assertions.assertDoesNotThrow(()->{
            customerService.deleteCustomer(customer.getId(),true);

        });

    }
    @Test
    void throws_CustomerNotFound_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo4@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(2000));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        customerService.deleteCustomer(customer.getId(),true);

        Assertions.assertThrows(ServiceOperationNotFoundException.CustomerNotFoundException.class ,()-> customerService.getCustomer(customer.getId()));

    }
    @Test
    void throws_AlreadyDeleted_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo5@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        customerService.deleteCustomer(customer.getId(),false);

        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.CustomerAlreadyDeletedException.class ,()-> customerService.getCustomer(customer.getId()));

    }
    @Test
    void should_Not_Delete_Customer_When_AccountBalance_Is_NotZero(){

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo6@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(100));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationCanNotDeleteException.CustomerBalanceNotZero.class,()-> customerService.deleteCustomer(customer.getId(),true));


    }
    @Test
    void should_Not_Delete_Customer_When_CardBalance_Is_NotZero(){

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo7@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(100));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationCanNotDeleteException.CustomerBalanceNotZero.class,()-> customerService.deleteCustomer(customer.getId(),true));


    }
    @Test
    void should_Update_Customer_Successfully(){
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo05@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));

        customerRepository.save(customer);

        Assertions.assertDoesNotThrow(()-> customerService.updateCustomer(new CustomerUpdateRequestDTO("Hasan" ,customer.getSurname(),customer.getMail(),customer.getPhone(),customer.getId())));



    }
}
