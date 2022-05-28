package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.facade.UserFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        return userFacade.getUser(id);
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        return userFacade.getAllUsers();
    }

  /*  @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {

        return userFacade.addUser(userCreateRequestDTO);
    }*/
}



