package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.AccountConverter;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationCanNotDeleteException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Card;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.enums.AccountType;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final CustomerService customerService;

    @Override
    @Transactional
    public Account addAccount(AccountCreateRequestDTO accountCreateRequestDTO) {
        Customer tempCustomer = customerService.getCustomer(accountCreateRequestDTO.customerId());
        AccountType accountType = accountCreateRequestDTO.accountType();

        boolean hasAnotherCheckingAccount = hasAnotherAccountOnSameBranch(AccountType.CHECKING_ACCOUNT, accountCreateRequestDTO, tempCustomer);
        boolean hasAnotherDepositAccount = hasAnotherAccountOnSameBranch(AccountType.DEPOSIT_ACCOUNT, accountCreateRequestDTO, tempCustomer);

        if (!hasAnotherCheckingAccount && !hasAnotherDepositAccount) {
            Account account = accountConverter.convertToAccount(accountCreateRequestDTO);
            accountRepository.save(account);
            return account;

        } else if (hasAnotherCheckingAccount && accountType.equals(AccountType.CHECKING_ACCOUNT)) {
            throw new ServiceOperationCanNotCreateException.AccountIsAlreadyCreatedException("Customer already has a Checking Account at this branch. ");

        } else if (hasAnotherDepositAccount && accountType.equals(AccountType.DEPOSIT_ACCOUNT))
            throw new ServiceOperationCanNotCreateException.AccountIsAlreadyCreatedException("Customer already has a Deposit account at this branch. ");

        else if (hasAnotherCheckingAccount && accountType.equals(AccountType.DEPOSIT_ACCOUNT)) {
            Account account = accountConverter.convertToAccount(accountCreateRequestDTO);
            accountRepository.save(account);
            return account;
        }
            Account account = accountConverter.convertToAccount(accountCreateRequestDTO);
            accountRepository.save(account);
            return account;


    }

    //@PostFilter("filterObject.body.owner=authentication.name")
    @Override
    public Account getAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.AccountNotFoundException("Account is not found"));
        if (account.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted");
        }
        return account;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountRepository.findAccountsByIsDeleted(false).stream().toList();
    }

    @Override
    public Account getAccountByIBAN(String iban) {
        Account account = accountRepository.findAccountByIBAN(iban);
        if(Objects.isNull(account)){
            throw new ServiceOperationNotFoundException.AccountNotFoundException("There is no account with this IBAN");
        }
        if (account.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted");
        }
        return account;
    }
   // @PostFilter("filterObject.customer.mail.equals(userRepository.findUserMailByUserId(authentication.principal.id))")
    //@PostFilter(value = "filterObject.accountNumber==userRepository.getById(authentication.principal.id).customer.accountList[id]")
    @Override
    public Collection<Account> getAllAccountOneCustomer(Long id) {
        return accountRepository.
                findAccountsByCustomer(customerService.getCustomer(id))
                .stream()
                .filter(accounts -> !accounts.isDeleted())
                .toList();
    }

    @Override
    @Transactional
    public String deleteAccount(Long id, boolean isHardDelete) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.AccountNotFoundException("Account is not found"));

        if (account.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account was deleted softly");
        }

        BigDecimal balance = account.getBalance();


        if ((balance.compareTo(BigDecimal.ZERO) == 0)) {
            Collection<Card> cardDebtCollection = getCardsBalance(account);
            Collection<Card> cardBalanceCollection = getCardsDebt(account);
            if (cardDebtCollection.isEmpty() && cardBalanceCollection.isEmpty()) {
                Customer customer = customerService.getCustomer(account.getCustomer().getId());
                if (account.isDeleted()) {
                    throw new ServiceOperationAlreadyDeletedException.AccountAlreadyDeletedException("Account is already deleted");
                }
                if (isHardDelete) {
                    customer.removeAccountFromCustomer(Set.of(account));
                    accountRepository.removeAccountById(id);
                    return "Account is hard deleted  successfully";
                }
                account.setDeleted(true);
                accountRepository.save(account);
                return "Account is soft deleted successfully";
            }
            throw new ServiceOperationCanNotDeleteException.AccountCardBalanceOrDebtNotZero("Account can not delete because one of card of this account has debt or balance");
        }

        throw new ServiceOperationCanNotDeleteException.AccountBalanceNotZero("Account balance is not 0 so you can not delete this account");

    }


    public boolean hasAnotherAccountOnSameBranch(AccountType accountType, AccountCreateRequestDTO accountCreateRequestDTO, Customer customer) {
        return customer.getAccountList()
                .stream()
                .filter(i -> i.getAccountType().equals(accountType))
                .map(Account::getBankBranchCode).anyMatch(i -> i.equals(accountCreateRequestDTO.branchCode()));
    }

    public Collection<Card> getCardsBalance(Account account) {
        return account
                .getCardList().stream().filter(c -> c.getCardBalance().compareTo(BigDecimal.ZERO) > 0).toList();
    }

    public Collection<Card> getCardsDebt(Account account) {
        return account
                .getCardList().stream().filter(c -> c.getCardDebt().compareTo(BigDecimal.ZERO) > 0).toList();
    }


}
