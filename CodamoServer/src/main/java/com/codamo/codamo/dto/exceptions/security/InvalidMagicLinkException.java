package com.codamo.codamo.dto.exceptions.security;

public class InvalidMagicLinkException extends Exception {

    public InvalidMagicLinkException() {
        super("Invalid Magic Link");
    }
}
