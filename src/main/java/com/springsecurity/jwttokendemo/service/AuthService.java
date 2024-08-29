package com.springsecurity.jwttokendemo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springsecurity.jwttokendemo.dto.LoginDto;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {

       Authentication authentication = 
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(),
        loginDto.getPassword()
       )); 

       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token= jwtTokenProvider.generateToken(authentication);
   
       return token;
      
    }

  

  

}
