package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    Customer getCustomerByName(String name);

    Customer getCustomerByEMail(String name);

    void removeCustomerById(Long id);

   // Customer findCustomerByEMail(String EMail);
    @Query("SELECT c FROM Customer c Where c.eMail=?1")
    Customer findCustomerByEMailAddress(String email);

    @Query("SELECT c FROM Customer c WHERE c.isDeleted = ?1")
    Collection<Customer> findAllCustomersByDeleteStatusByJPQL(boolean status);


    Customer getById(Long id);
}

