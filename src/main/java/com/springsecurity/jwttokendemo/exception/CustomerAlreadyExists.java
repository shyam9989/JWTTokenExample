package com.springsecurity.jwttokendemo.exception;

public class CustomerAlreadyExists extends RuntimeException {

    private String msg;

   public CustomerAlreadyExists() {
    }

    public CustomerAlreadyExists(String msg) {
        this.msg = msg;
    }
}
