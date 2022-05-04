package com.example.productorderchain.repository;

import com.example.productorderchain.model.BasketItem;
import com.example.productorderchain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public interface BasketItemRepository extends JpaRepository<BasketItem,Long> {


    Collection<BasketItem> findAllByIsDeleted(boolean deleted);

    @Transactional
    @Modifying
    @Query("DELETE FROM BasketItem as b WHERE b.id = ?1")
    void deleteByIdWithJPQL(Long id);

    @Query("SELECT b FROM BasketItem b WHERE b.isDeleted = ?1")
    Collection<BasketItem> findAllProductsByDeleteStatusByJPQL(boolean status);
}
