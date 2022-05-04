package com.example.productorderchain.repository;

import com.example.productorderchain.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    @Query("SELECT bs FROM Basket bs WHERE bs.isDeleted = ?1")
    Collection<Basket> findAllBasketByDeleteStatusByJPQL(boolean status);
}
