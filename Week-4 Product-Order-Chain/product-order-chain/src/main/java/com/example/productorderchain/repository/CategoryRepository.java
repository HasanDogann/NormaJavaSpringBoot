package com.example.productorderchain.repository;

import com.example.productorderchain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c WHERE c.isDeleted = ?1")
    Collection<Category> findAllBrandsByDeleteStatusByJPQL(boolean status);
}
