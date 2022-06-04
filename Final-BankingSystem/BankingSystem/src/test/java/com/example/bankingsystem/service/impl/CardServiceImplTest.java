package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.exception.TransferOperationException;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.CardPaymentRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.*;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
@Slf4j
class CardServiceImplTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardService cardService;

    @Test
    public void adding_BankCard_successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo16@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
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


        Assertions.assertDoesNotThrow(() -> {
            cardService.addCard(

                    new CardCreateRequestDTO(account.getId(), CardType.BANK_CARD, BigDecimal.valueOf(1000)));
        });


    }

    @Test
    public void adding_CreditCard_successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo17@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
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


        Assertions.assertDoesNotThrow(() -> {
            cardService.addCard(

                    new CardCreateRequestDTO(account.getId(), CardType.CREDIT_CARD, BigDecimal.valueOf(1000)));
        });


    }

    @Test
    public void throws_Getting_Card_IsNotFound_Exception() {


        Assertions.assertThrows(ServiceOperationNotFoundException.CardNotFoundException.class,
                () -> {
                    cardService.getCard(01010101L);
                });


    }

    @Test
    public void throws_Getting_Card_IsDeleted_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo18@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        cardService.deleteCard(bankCard.getId(), false);

        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException.class,
                () -> {
                    cardService.getCard(bankCard.getId());
                });


    }

    @Test
    public void when_Getting_CardBalance_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo19@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(1520));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(
                () -> {
                    cardService.getCardBalance(bankCard.getCardNo());
                });


    }

    @Test
    void paying_Card_Debt_WithAccount() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo019@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(1000));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setCardDebt(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        cardService.payCardDebt(new CardPaymentRequestDTO(BigDecimal.valueOf(750), bankCard.getCardNo(), PaymentType.BY_ACCOUNT));
        Assertions.assertTrue(cardService.getCardBalance(bankCard.getCardNo()).getCardBalance().compareTo(BigDecimal.valueOf(750)) == 0);

    }

    @Test
    void throws_paying_Card_Debt_WithAccount_NotEnoughAmount_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo020@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setCardDebt(BigDecimal.valueOf(1000));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(TransferOperationException.PaymentCanNotProceedException.class,
                () -> {
                    cardService.payCardDebt(new CardPaymentRequestDTO(BigDecimal.valueOf(1000), bankCard.getCardNo(), PaymentType.BY_ACCOUNT));
                });


    }

    @Test
    void paying_Card_Debt_With_ATM() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo021@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.CREDIT_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(1000));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setCardDebt(BigDecimal.valueOf(1000));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        cardService.payCardDebt(new CardPaymentRequestDTO(BigDecimal.valueOf(500), bankCard.getCardNo(), PaymentType.BY_ATM));

        Assertions.assertTrue(cardService.getCardBalance(bankCard.getCardNo()).getCardDebt().compareTo(BigDecimal.valueOf(500)) == 0);

    }

    @Test
    void adding_Card_Balance_With_ATM() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo022@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setCardDebt(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        cardService.payCardDebt(new CardPaymentRequestDTO(BigDecimal.valueOf(1000), bankCard.getCardNo(), PaymentType.BY_ATM));
        Assertions.assertTrue(cardService.getCardBalance(bankCard.getCardNo()).getCardBalance().compareTo(BigDecimal.valueOf(1000)) == 0);


    }

    @Test
    public void throws_Getting_CardBalance_CardIsNotFound_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo20@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(1520));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationNotFoundException.CardNotFoundException.class,
                () -> {
                    cardService.getCardBalance("1234123412341234");
                });


    }

    @Test
    public void getting_AllCardsOfOneAccount_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo21@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(1520));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(
                () -> {
                    cardService.getAllCardByAccountNumber(account.getId());
                });


    }

    @Test
    public void throws_AllCardsOfOneAccount_NoCardOnAccount_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo22@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationNotFoundException.CardNotFoundException.class,
                () -> {
                    cardService.getAllCardByAccountNumber(account.getId());
                });


    }

    @Test
    public void getting_AllCardsOfOneCustomer_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo23@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(1520));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(
                () -> {
                    cardService.getAllCardByCustomerId(customer.getId());
                });


    }

    @Test
    public void throws_AllCardsOfOneCustomer_NoCardOnCustomer_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo24@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        customer.addAccountToCustomer(Set.of(account));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(() -> {
            cardService.getAllCardByCustomerId(customer.getId());
        });


    }

    @Test
    public void deleting_Card_Soft_And_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo25@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(
                () -> {
                    cardService.deleteCard(bankCard.getId(), false);
                });


    }

    @Test
    public void deleting_Card_Hard_And_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo26@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(
                () -> {
                    cardService.deleteCard(bankCard.getId(), true);
                });


    }

    @Test
    public void throws_deleting_Card_IsDeleted_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo27@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        cardService.deleteCard(bankCard.getId(), false);

        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.CardAlreadyDeletedException.class,
                () -> {
                    cardService.deleteCard(bankCard.getId(), false);
                });


    }

    @Test
    public void throws_deleting_Card_IsNotFound_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo28@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(100));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        cardService.deleteCard(bankCard.getId(), true);

        Assertions.assertThrows(ServiceOperationNotFoundException.CardNotFoundException.class,
                () -> {
                    cardService.deleteCard(bankCard.getId(), false);
                });


    }

    @Test
    public void throws_deleting_Card_BalanceNotZero_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo29@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(0));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
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


        Assertions.assertThrows(ServiceOperationCanNotDeleteException.AccountCardBalanceOrDebtNotZero.class,
                () -> {
                    cardService.deleteCard(bankCard.getId(), false);
                });


    }


}