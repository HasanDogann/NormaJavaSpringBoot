package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.entity.Customer;
import com.example.bankingsystem.model.entity.CustomerAddress;
import com.example.bankingsystem.model.entity.enums.Role;
import com.example.bankingsystem.repository.CustomerRepository;
import com.example.bankingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author Hasan DOĞAN
 * BankingSystemApplication.java
 * 4.06.2022
 */
@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void adding_User_Successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo62@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);

        Assertions.assertDoesNotThrow(() -> {
            userService.addUser(new UserCreateRequestDTO(
                    customer.getMail(), "1234Asd.", Role.USER, customer.getId()
            ));
        });
    }

    @Test
    void throws_adding_User_Mail_AlreadyTaken_Exception() {

        Assertions.assertThrows(ServiceOperationCanNotCreateException.UserIsAlreadyCreatedException.class, () -> {
            userService.addUser(new UserCreateRequestDTO(
                    "Demo@mail", "1234Asd.", Role.USER, 1L
            ));
        });
    }

    @Test
    void throws_User_CanNotFound_Exception() {

        Assertions.assertThrows(ServiceOperationNotFoundException.UserNotFoundException.class,
                () -> userService.getUser(100000L));
    }

    @Test
    void throws_User_IsDeleted_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo63@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));

        Long id = userService.getUserByEmail("Demo63@mail").getId();
        userService.deleteUser(id, false);
        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.UserAlreadyDeletedException.class, () -> {
            userService.getUser(id);
        });
    }

    @Test
    void deleting_User_Successfully() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo64@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));
        Long id = userService.getUserByEmail("Demo64@mail").getId();

        Assertions.assertDoesNotThrow(() ->
                userService.deleteUser(id, false));
    }

    @Test
    void throws_deleting_User_AlreadyDeleted_Exception() {
        Customer customer = new Customer();
        customer.setName("Demo");
        customer.setSurname("Customer");
        customer.setMail("Demo65@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));

        Long id = userService.getUserByEmail("Demo65@mail").getId();
        userService.deleteUser(id, false);
        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.UserAlreadyDeletedException.class, () -> {
            userService.deleteUser(id, false);
        });
    }
}