package com.example.bankingsystem.controller;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.facade.UserFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserFacade userFacade;
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable @Min(1) Long id) {

        return userFacade.getUser(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        return userFacade.getAllUsers();
    }
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
   @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO) {

        return userFacade.addUser(userCreateRequestDTO);
    }
}



