package com.example.productorderchain.repository;

import com.example.productorderchain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Collection<Product> findAllByIsDeleted(boolean deleted);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = ?1")
    Collection<Product> findAllProductsByDeleteStatusByJPQL(boolean status);


}
