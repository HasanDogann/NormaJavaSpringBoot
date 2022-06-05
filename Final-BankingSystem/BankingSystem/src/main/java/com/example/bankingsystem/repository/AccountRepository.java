package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Account;
import com.example.bankingsystem.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    Account getById(Long id);

    Collection<Account> findAccountsByIsDeleted(boolean isDeletedOne);

    Account findAccountByIBAN(String IbanNo);

    Collection<Account> findAccountsByCustomer(Customer customer);

    void removeAccountById(Long id);
}
