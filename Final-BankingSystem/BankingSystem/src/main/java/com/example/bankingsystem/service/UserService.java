package com.example.bankingsystem.service;

import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */

@Service
public interface UserService  {


    void addUser(UserCreateRequestDTO userCreateRequestDTO);

    User getUser(Long id);


    Collection<User> getAllUsers();

    User getUserByEmail(String email);

    String deleteUser(Long id,boolean isHardDelete);
}
