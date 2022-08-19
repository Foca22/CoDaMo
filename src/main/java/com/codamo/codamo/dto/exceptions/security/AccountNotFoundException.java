package com.codamo.codamo.dto.exceptions.security;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
