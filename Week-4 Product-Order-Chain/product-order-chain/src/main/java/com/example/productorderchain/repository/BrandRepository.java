package com.example.productorderchain.repository;

import com.example.productorderchain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    @Query("SELECT b FROM Brand b WHERE b.isDeleted = ?1")
    Collection<Brand> findAllBrandsByDeleteStatusByJPQL(boolean status);
}
