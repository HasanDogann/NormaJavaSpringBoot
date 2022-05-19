package com.example.bankingsystem.repository;

import com.example.bankingsystem.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Override
    Account getById(Long id);

   Collection<Account> findAccountsByIsDeleted(boolean isDeletedOne);

   Account findAccountByIBAN(String IbanNo);

   Collection<Account> findAccountByCustomer(Customer customer);

   void removeAccountById(Long id);
}
