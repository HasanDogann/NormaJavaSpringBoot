package com.example.productorderchain.repository;

import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BasketItemRepository extends JpaRepository<BasketItem,Long> {

    @Query("SELECT b FROM BasketItem b WHERE b.isDeleted = ?1")
    Collection<BasketItem> findAllProductsByDeleteStatusByJPQL(boolean status);
}
