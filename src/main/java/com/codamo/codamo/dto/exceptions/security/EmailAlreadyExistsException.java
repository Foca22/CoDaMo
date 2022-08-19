package com.codamo.codamo.dto.exceptions.security;

public class EmailAlreadyExistsException  extends Exception {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
