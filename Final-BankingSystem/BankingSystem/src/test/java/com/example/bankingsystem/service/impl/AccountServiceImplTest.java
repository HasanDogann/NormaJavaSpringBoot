package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.core.constants.ConstantUtils;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.AccountStatus;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.model.entity.enums.BalanceCurrencyType;
import com.example.bankingsystem.model.entity.enums.CardType;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;



    @Test
    public void account_Is_Not_Found_Exception8() {
        Assertions.assertThrows(ServiceOperationNotFoundException.AccountNotFoundException.class,
                () -> {
                    accountService.getAccount(1000L);
                });
    }

    @Test
    public void accountType_IsChecking_Already_Exist_At_Same_Branch() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo9@mail");
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

        //adding another account
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        Assertions.assertThrows(ServiceOperationCanNotCreateException.AccountIsAlreadyCreatedException.class,
                () -> {
                    accountService.addAccount(new AccountCreateRequestDTO(account2.getAccountType(), account2.getBalanceCurrencyType(),
                            account2.getBankBranchCode(), account2.getCustomer().getId()));
                });
    }

    @Test
    public void accountType_IsDeposit_Already_Exist_At_Same_Branch() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo10@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
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

        //adding another account
        Account account2 = new Account();
        account2.setAccountType(AccountType.DEPOSIT_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9999);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        Assertions.assertThrows(ServiceOperationCanNotCreateException.AccountIsAlreadyCreatedException.class,
                () -> {
                    accountService.addAccount(new AccountCreateRequestDTO(account2.getAccountType(), account2.getBalanceCurrencyType(),
                            account2.getBankBranchCode(), account2.getCustomer().getId()));
                });
    }

    @Test
    public void should_throw_When_Proceed_Wrong_IBAN_AccountNotFound_Exception() {

        Assertions.assertThrows(ServiceOperationNotFoundException.AccountNotFoundException.class,
                () -> {
                    accountService.getAccountByIBAN("12331231313123");
                });

    }

    @Test
    public void should_throw_Account_Was_Deleted_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo11@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
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
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        accountService.deleteAccount(account.getId(), false);

        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException.class,
                () -> {
                    accountService.deleteAccount(account.getId(), false);
                });

    }

    @Test
    public void should_throw_Account_Balance_NotZero_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo12@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
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
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationCanNotDeleteException.AccountBalanceNotZero.class,
                () -> {
                    accountService.deleteAccount(account.getId(), true);
                });

    }

    @Test
    public void should_throw_Card_Balance_or_Debt_NotZero_Exception() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo13@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
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
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setCardBalance(BigDecimal.valueOf(1000));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertThrows(ServiceOperationCanNotDeleteException.AccountCardBalanceorDebtNotZero.class,
                () -> {
                    accountService.deleteAccount(account.getId(), true);
                });

    }

    @Test
    public void should_Account_Was_Deleted_Successfully() {

        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo14@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        //Adding account infos
        Account account = new Account();
        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
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
        bankCard.setCardLimit(BigDecimal.valueOf(0));
        bankCard.setCardBalance(BigDecimal.valueOf(0));
        bankCard.setAccount(account);
        bankCard.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);


        Assertions.assertDoesNotThrow(() -> {
            accountService.deleteAccount(account.getId(), false);
        });

    }
    @Test
    public void adding_Account_successfully(){
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo15@mail");
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

        //adding another account
        Account account2 = new Account();
        account2.setAccountType(AccountType.CHECKING_ACCOUNT);
        account2.setBalanceCurrencyType(BalanceCurrencyType.TRY);
        account2.setAccountNumber(ConstantUtils.getRandomAccountNumber());
        account2.setBankBranchCode(9990);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setIBAN(ConstantUtils.getRandomIban(account.getBankBranchCode()) + "" + account.getAccountNumber() + "" + ConstantUtils.getRandomExtraAccountNo());
        account2.setCreationDate(ConstantUtils.getCurrentDate());
        account2.setAccountStatus(AccountStatus.ACTIVE);
        account2.setCustomer(customer);


        customer.addAccountToCustomer(Set.of(account));
        account.addCardToAccount(Set.of(bankCard));
        customerRepository.save(customer);

        Assertions.assertDoesNotThrow(
                () -> {
                    accountService.addAccount(new AccountCreateRequestDTO(account2.getAccountType(), account2.getBalanceCurrencyType(),
                            account2.getBankBranchCode(), account2.getCustomer().getId()));
                });
    }

}
