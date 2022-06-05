package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    void removeCustomerById(Long id);

    @Query("SELECT c FROM Customer c Where c.mail=?1")
    Customer findCustomerByEMailAddress(String email);

    @Query("SELECT c FROM Customer c WHERE c.isDeleted = ?1")
    Collection<Customer> findAllCustomersByDeleteStatusByJPQL(boolean status);


    Customer getById(Long id);
}

