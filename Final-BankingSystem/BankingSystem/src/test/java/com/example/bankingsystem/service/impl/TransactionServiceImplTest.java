package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.TransactionRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.*;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 4.06.2022
 */
@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;

    @Test
    void success_MoneyTransfer_With_IBAN_TRYtoTRY() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo30@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo31@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });


    }

    @Test
    void success_MoneyTransfer_With_IBAN_EURtoTRY() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo32@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.EUR);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo33@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });


    }

    @Test
    void success_MoneyTransfer_With_IBAN_USDtoTRY() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo34@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo35@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });


    }

    @Test
    void success_MoneyTransfer_With_IBAN_TRYtoEUR() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo36@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo37@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.EUR);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });


    }

    @Test
    void success_MoneyTransfer_With_IBAN_TRYtoUSD() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo38@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo39@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });


    }

    @Test
    void throws_MoneyTransfer_With_IBAN_NotEnoughMoneyOnSender_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo40@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo41@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertThrows(TransferOperationException.TransferCanNotProceedException.class, () ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });

    }

    @Test
    void throws_MoneyTransfer_With_IBAN_SenderAccountCanNotBe_DepositAccount_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo42@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo43@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertThrows(TransferOperationException.TransferCanNotProceedException.class, () ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });

    }

    @Test
    void throws_MoneyTransfer_With_Wrong_IBAN_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo44@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo45@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertThrows(ServiceOperationNotFoundException.AccountNotFoundException.class, () ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), account.getIBAN(), "TR12345678901234567890123", TransferType.IBAN));
        });

    }

    @Test
    void throws_MoneyTransfer_With_Deleted_Account_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo46@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo47@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        accountService.deleteAccount(account2.getId(), false);

        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException.class, () ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });

    }

    @Test
    void throws_MoneyTransfer_Account_NotFound_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo48@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo49@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        customerRepository.saveAll(Set.of(customer, customer2));

        accountService.deleteAccount(account2.getId(), true);

        Assertions.assertThrows(ServiceOperationNotFoundException.AccountNotFoundException.class, () ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), account.getIBAN(), account2.getIBAN(), TransferType.IBAN));
        });

    }

    @Test
    void success_Purchase_With_CreditCard() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo50@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo51@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card creditCard = new Card();
        creditCard.setCardNo(ConstantUtils.getRandomCardNo());
        creditCard.setCardType(CardType.CREDIT_CARD);
        creditCard.setCardLimit(BigDecimal.valueOf(100));
        creditCard.setCardBalance(BigDecimal.valueOf(100));
        creditCard.setAccount(account2);
        creditCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(creditCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), creditCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }

    @Test
    void success_Purchase_With_BankCard() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo52@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo53@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(100));
        bankCard.setAccount(account2);
        bankCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(bankCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), bankCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }

    @Test
    void Throws_Purchase_With_CreditCard_NotEnoughLimit_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo54@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo55@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card creditCard = new Card();
        creditCard.setCardNo(ConstantUtils.getRandomCardNo());
        creditCard.setCardType(CardType.CREDIT_CARD);
        creditCard.setCardLimit(BigDecimal.valueOf(100));
        creditCard.setCardBalance(BigDecimal.valueOf(100));
        creditCard.setAccount(account2);
        creditCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(creditCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertThrows(TransferOperationException.TransferCanNotProceedException.class,() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), creditCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }

    @Test
    void Throws_Purchase_With_BankCard_NotEnoughLimit_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo56@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo57@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(100));
        bankCard.setAccount(account2);
        bankCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(bankCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertThrows(TransferOperationException.TransferCanNotProceedException.class,() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(250), bankCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }

    @Test
    void success_Purchase_With_Foreign_CreditCard() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo58@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo59@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.EUR);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card creditCard = new Card();
        creditCard.setCardNo(ConstantUtils.getRandomCardNo());
        creditCard.setCardType(CardType.CREDIT_CARD);
        creditCard.setCardLimit(BigDecimal.valueOf(100));
        creditCard.setCardBalance(BigDecimal.valueOf(100));
        creditCard.setAccount(account2);
        creditCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(creditCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), creditCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }

    @Test
    void success_Purchase_With_Foreign_BankCard() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo60@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(200));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setName("Demo");
        customer2.setSurname("Customer");
        customer2.setMail("Demo61@mail");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));

        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.USD);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer2);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(100));
        bankCard.setAccount(account2);
        bankCard.setCustomer(customer2);


        customer.addAccountToCustomer(Set.of(account));
        customer2.addAccountToCustomer(Set.of(account2));
        account2.addCardToAccount(Set.of(bankCard));
        customerRepository.saveAll(Set.of(customer, customer2));

        Assertions.assertDoesNotThrow(() ->
        {
            transactionService.moneyTransfer(new TransactionRequestDTO(BigDecimal.valueOf(50), bankCard.getCardNo(), account2.getIBAN(), TransferType.PURCHASE));
        });


    }


}