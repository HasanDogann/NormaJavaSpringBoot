package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 21.05.2022
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

    @Query("SELECT u.mail FROM User u WHERE u.id=?1 ")
    String findUserMailByUserId(Long id);

    @Query("SELECT u FROM User u Where u.mail=?1")
    User findUserByEMailAddress(String email);

    @Query("SELECT u FROM User u WHERE u.isDeleted = ?1")
    Collection<User> findAllUsersByDeleteStatusByJPQL(boolean status);

}
