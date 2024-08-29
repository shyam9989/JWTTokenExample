package com.springsecurity.jwttokendemo.service;

import com.springsecurity.jwttokendemo.dto.LoginDto;

public interface AuthServiceInterface {

    String login(LoginDto loginDto);
    
}
