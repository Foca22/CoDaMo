package com.codamo.codamo.dto.exceptions.security;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
        super("Wrong Password provided");
    }
}
