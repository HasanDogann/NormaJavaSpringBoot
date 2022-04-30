package com.example.productorderchain.repository;

import com.example.productorderchain.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Long> {

}
