package com.codamo.codamo.dto.exceptions.security;

public class PasswordsNotMatchException extends Exception {

    public PasswordsNotMatchException() {
        super("Password and confirm password not match");
    }
}
