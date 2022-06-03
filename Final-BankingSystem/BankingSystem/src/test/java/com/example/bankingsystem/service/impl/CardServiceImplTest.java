package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.CardConverter;
import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.dto.request.CardCreateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceImplTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardService cardService;

    @Test
    public void adding_BankCard_successfully(){
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


        Assertions.assertDoesNotThrow(()->{
            cardService.addCard(

                    new CardCreateRequestDTO(account.getId(), CardType.BANK_CARD,BigDecimal.valueOf(1000)));
        });



    }
    @Test
    public void adding_CreditCard_successfully(){
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


        Assertions.assertDoesNotThrow(()->{
            cardService.addCard(

                    new CardCreateRequestDTO(account.getId(), CardType.CREDIT_CARD,BigDecimal.valueOf(1000)));
        });



    }



}