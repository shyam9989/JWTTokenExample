package com.springsecurity.jwttokendemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.jwttokendemo.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    
}
