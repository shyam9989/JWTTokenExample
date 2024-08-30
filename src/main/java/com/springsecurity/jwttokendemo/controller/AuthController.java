package com.springsecurity.jwttokendemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwttokendemo.dto.AuthResponseDto;
import com.springsecurity.jwttokendemo.dto.LoginDto;
import com.springsecurity.jwttokendemo.exception.CustomerAlreadyExists;
import com.springsecurity.jwttokendemo.model.Role;
import com.springsecurity.jwttokendemo.model.User;
import com.springsecurity.jwttokendemo.repository.RoleRepository;
import com.springsecurity.jwttokendemo.repository.UserRepository;
import com.springsecurity.jwttokendemo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
    String token = authService.login(loginDto);
    AuthResponseDto authResponseDto = new AuthResponseDto();
    authResponseDto.setAccessToken(token);

    return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
    return new ResponseEntity<>("duplicate email found", HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user) {

    System.out.println("user is" + user.toString());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user = userRepository.save(user);
    if (user == null) {
      throw new CustomerAlreadyExists("customer exists");
    }
    return new ResponseEntity<>(user, HttpStatus.OK);

  }

  @PostMapping("/addRole")
  public ResponseEntity<Role> addRole(@RequestBody Role role) {

    System.out.println("user is" + role.toString());
    role = roleRepository.save(role);
    return new ResponseEntity<>(role, HttpStatus.OK);

  }

}
