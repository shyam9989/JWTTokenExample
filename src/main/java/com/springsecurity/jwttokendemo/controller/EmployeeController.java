package com.springsecurity.jwttokendemo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class EmployeeController {

    @PreAuthorize("hasRole('ADMIN')")  
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")  
    public ResponseEntity<String> addUser() {   
        return ResponseEntity.ok("user added ");
    }
    
    
}
