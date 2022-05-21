package com.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/try")
public class TryController {
    

    @GetMapping("/{name}")
    public ResponseEntity<String> sayName(@PathVariable String name){
        return ResponseEntity.ok().body(name);
    }

}
