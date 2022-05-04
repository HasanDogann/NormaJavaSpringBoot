package com.example.productorderchain.repository;

import com.example.productorderchain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.isDeleted = ?1")
    Collection<Order> findAllProductsByDeleteStatusByJPQL(boolean status);
}
