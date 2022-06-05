package com.example.bankingsystem.model.entity.base;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.entity.*;
import com.example.bankingsystem.model.entity.enums.*;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */
@Component
@RequiredArgsConstructor
public class InitialCustomerCreator {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ConstantUtils constantUtils;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createFirstCustomer() {
        //Initial Costumer 1
        Customer customer = new Customer();
        customer.setName("Admin");
        customer.setSurname("Customer");
        customer.setMail("Admin@mail");
        customer.setPhone("090123456789");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Initial Costumer 2
        Customer customer2 = new Customer();
        customer2.setName("User");
        customer2.setSurname("Customer");
        customer2.setMail("User@mail");
        customer2.setPhone("9012345678");
        customer2.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer2));


        //Initial Account 1
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(5000));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        //Initial Account 2
        Account account2 = new Account();
        account2.setAccountType(AccountType.DEPOSIT_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.EUR);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9998);
        account2.setBalance(BigDecimal.valueOf(100));
        account2.setIBAN(ConstantUtils.getRandomIban(account2.getBankBranchCode()) + "" + account2.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer);

        //Initial Account 3
        Account account3 = new Account();
        account3.setAccountType(AccountType.CHECKING_ACCOUNT);
        account3.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account3.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account3.setBankBranchCode(9997);
        account3.setBalance(BigDecimal.valueOf(1000));
        account3.setIBAN(ConstantUtils.getRandomIban(account3.getBankBranchCode()) + "" + account3.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account3.setCreationDate(ConstantUtils.getCurrentDate());
        account3.setAccountStatus(AccountStatus.ACTIVE);
        account3.setCustomer(customer2);

        ////Initial Card 1
        Card creditCard = new Card();
        creditCard.setCardNo(ConstantUtils.getRandomCardNo());
        creditCard.setCardType(CardType.CREDIT_CARD);
        creditCard.setCardLimit(BigDecimal.valueOf(5000));
        creditCard.setCardDebt(BigDecimal.valueOf(500));
        creditCard.setAccount(account);
        creditCard.setCustomer(customer);

        //Initial Card 2
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);

        //Initial Card 2
        Card bankCard2 = new Card();
        bankCard2.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard2.setCardType(CardType.BANK_CARD);
        bankCard2.setCardBalance(BigDecimal.valueOf(500));
        bankCard2.setCardLimit(BigDecimal.valueOf(0));
        bankCard2.setAccount(account3);
        bankCard2.setCustomer(customer2);


        //Adding accounts to Customer's account list
        customer.addAccountToCustomer(Set.of(account));
        customer.addAccountToCustomer(Set.of(account2));
        customer2.addAccountToCustomer(Set.of(account3));

        //Adding cards to Account's card list
        account.addCardToAccount(Set.of(creditCard));
        account.addCardToAccount(Set.of(bankCard));
        account3.addCardToAccount(Set.of(bankCard2));


        //Saving customer to DB
        customerRepository.saveAll(Set.of(customer,customer2));

        //Adding Admin User to DB
        User user = new User();
        user.setCustomer(customer);
        user.setMail(customer.getMail());
        user.setPassword(passwordEncoder.encode("1234Asd."));
        user.setRole(Role.ADMIN);

        userRepository.save(user);


    }


}
