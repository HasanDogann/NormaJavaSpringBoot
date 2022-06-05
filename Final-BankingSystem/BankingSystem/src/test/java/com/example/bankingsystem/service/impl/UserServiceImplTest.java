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
 * Author Hasan DOGAN
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
    void should_Add_User_Successfully() {
        Customer customer = new Customer();
        customer.setName("Test 51");
        customer.setSurname("Customer");
        customer.setMail("Test51@mail");
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
    void throws_adding_User_Mail_AlreadyCreated_Exception() {

        Assertions.assertThrows(ServiceOperationCanNotCreateException.UserIsAlreadyCreatedException.class, () -> {
            userService.addUser(new UserCreateRequestDTO(
                    "Admin@mail", "1234Asd.", Role.USER, 1L
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
        customer.setName("Test 52");
        customer.setSurname("Customer");
        customer.setMail("Test52@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));

        Long id = userService.getUserByEmail("Test52@mail").getId();
        userService.deleteUser(id, false);
        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.UserAlreadyDeletedException.class, () -> {
            userService.getUser(id);
        });
    }

    @Test
    void should_Delete_User_Successfully() {
        Customer customer = new Customer();
        customer.setName("Test 53");
        customer.setSurname("Customer");
        customer.setMail("Test53@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));
        Long id = userService.getUserByEmail("Test53@mail").getId();

        Assertions.assertDoesNotThrow(() ->
                userService.deleteUser(id, false));
    }

    @Test
    void throws_deleting_User_AlreadyDeleted_Exception() {
        Customer customer = new Customer();
        customer.setName("Test 84");
        customer.setSurname("Customer");
        customer.setMail("Test84@mail");
        customer.setPhone("90123456");
        customer.setCustomerAddress(
                new CustomerAddress("TR", "Ankara", "06000", "Capital City", customer));

        customerRepository.save(customer);
        userService.addUser(new UserCreateRequestDTO(
                customer.getMail(), "1234Asd.", Role.USER, customer.getId()
        ));

        Long id = userService.getUserByEmail("Test84@mail").getId();
        userService.deleteUser(id, false);
        Assertions.assertThrows(ServiceOperationAlreadyDeletedException.UserAlreadyDeletedException.class, () -> {
            userService.deleteUser(id, false);
        });
    }
}