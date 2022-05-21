package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.repository.UserRepository;
import com.example.bankingsystem.security.JsonWTUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEMailAddress(email);
        return JsonWTUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException{
        User user = userRepository.getById(id);
        return JsonWTUserDetails.create(user);
    }

}
