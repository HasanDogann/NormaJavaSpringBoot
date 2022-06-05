package com.example.bankingsystem.controller;

import com.example.bankingsystem.facade.UserFacade;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.dto.response.UserGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 21.05.2022
 */

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserFacade userFacade;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponseDTO> getUser(@PathVariable @Min(1) Long id) {
        logger.trace("Get method used for getting User: {}", id);
        return userFacade.getUser(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<UserGetResponseDTO>> getAllUsers() {
        logger.trace("Get method used for getting all Users. ");
        return userFacade.getAllUsers();
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addUser(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        logger.trace("Post method used for adding new User. ");
        return userFacade.addUser(userCreateRequestDTO);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @Min(1) @PathVariable Long id,
                                             @RequestParam(name = "isHardDelete", required = false) boolean isHardDelete) {
        logger.trace("Delete method used for deleting  User: {}", id);
        return userFacade.deleteUser(id, isHardDelete);
    }

}



