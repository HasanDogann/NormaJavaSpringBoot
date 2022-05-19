package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.repository.CustomerRepository;
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

    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEMailAddress(email);
        return JsonWTUserDetails.create(customer);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException{
        Customer customer= customerRepository.getById(id);
        return JsonWTUserDetails.create(customer);
    }

}
