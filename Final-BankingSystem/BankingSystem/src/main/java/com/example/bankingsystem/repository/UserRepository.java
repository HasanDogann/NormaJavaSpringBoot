package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User getUserById(Long id);

    @Query("SELECT u FROM User u Where u.mail=?1")
    User findUserByEMailAddress(String email);

    @Query("SELECT u FROM User u WHERE u.isDeleted = ?1")
    Collection<User> findAllUsersByDeleteStatusByJPQL(boolean status);

}
