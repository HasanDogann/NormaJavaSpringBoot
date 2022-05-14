package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.isDeleted = ?1")
    Collection<Customer> findAllCustomersByDeleteStatusByJPQL(boolean status);

}
