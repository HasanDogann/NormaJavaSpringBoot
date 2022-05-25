package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.model.dto.request.AccountCreateRequestDTO;
import com.example.bankingsystem.model.dto.request.UserCreateDTO;
import com.example.bankingsystem.model.dto.response.AccountGetResponseDTO;
import com.example.bankingsystem.model.dto.response.UserResponseDTO;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {

private final UserService userService;
private final UserConverter userConverter;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        Collection<UserResponseDTO> users = userService.getAllUsers().stream()
                .map(userConverter::toUserResponseFromUser).toList();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserCreateDTO userCreateDTO) {
        userService.addUser(userCreateDTO);
        return ResponseEntity.ok().body("User is added successfully");
    }



}
