package com.example.bankingsystem.model.entity.base;
import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;

import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Hasan DOĞAN
 *  IntelliJ IDEA
 *  23.05.2022
 */
@Component
@RequiredArgsConstructor
public class InitialCustomerCreator {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final ConstantUtils constantUtils;

    @PostConstruct
    public void createFirstCustomer(){
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer));
        Customer customer2 = new Customer();
        customer2.setName("Demo 2");
        customer2.setSurname("Customer");
        customer2.setMail("Demo@mail2");
        customer2.setPhone("90123456");
        customer2.setCustomerAddress(
                new CustomerAddress("TR","Ankara","06000","Capital City",customer2));
        //Adding account infos
        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.CHECKING_ACCOUNT);
        account.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account.setBankBranchCode(9999);
        account.setBalance(BigDecimal.valueOf(1250));
        account.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account.setCreationDate(ConstantUtils.getCurrentDate());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);
        //Adding account infos
        Account account2 = new Account();
        account2.setAccountType(AccountType.DEPOSIT_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.EUR);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9998);
        account2.setBalance(BigDecimal.valueOf(750));
        account2.setIBAN(ConstantUtils.getRandomIban(account2.getBankBranchCode())+""+account2.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer);

        //Adding Market Account
        Account account3 = new Account();
        account3.setAccountType(AccountType.CHECKING_ACCOUNT);
        account3.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account3.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account3.setBankBranchCode(9997);
        account3.setBalance(BigDecimal.valueOf(1000));
        account3.setIBAN(ConstantUtils.getRandomIban(account3.getBankBranchCode())+""+account3.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
       // account3.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode())+""+account.getAccountNumber()+""+ConstantUtils.getRandomExtraAccountNo());
        account3.setCreationDate(ConstantUtils.getCurrentDate());
        account3.setAccountStatus(AccountStatus.ACTIVE);
        account3.setCustomer(customer2);

        //Adding credit card infos
        Card creditCard = new Card();
        creditCard.setCardNo(ConstantUtils.getRandomCardNo());
        creditCard.setCardType(CardType.CREDIT_CARD);
        creditCard.setCardLimit(BigDecimal.valueOf(5000));
        creditCard.setCardDebt(BigDecimal.valueOf(1000));
        creditCard.setAccount(account);
        creditCard.setCustomer(customer);
        //Adding bank card infos
        Card bankCard = new Card();
        bankCard.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard.setCardType(CardType.BANK_CARD);
        bankCard.setCardLimit(BigDecimal.valueOf(2000));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);
        //Adding bank card infos
        Card bankCard2 = new Card();
        bankCard2.setCardNo(ConstantUtils.getRandomCardNo());
        bankCard2.setCardType(CardType.BANK_CARD);
        bankCard2.setCardBalance(BigDecimal.TEN);
        bankCard2.setCardLimit(BigDecimal.valueOf(3500));
        bankCard2.setAccount(account2);
        bankCard2.setCustomer(customer);


        //adding account to Customer's account list
        customer.addAccountToCustomer(Set.of(account));
        customer.addAccountToCustomer(Set.of(account2));
        customer.addAccountToCustomer(Set.of(account3));
        //Adding card to Account's card list
        account.addCardToAccount(Set.of(creditCard));
        account.addCardToAccount(Set.of(bankCard));
        account2.addCardToAccount(Set.of(bankCard2));


        //Adding customer to DB
        customerRepository.save(customer);

        //Adding account to DB
       // accountRepository.save(account);
       // accountRepository.save(account2);
    }



}
