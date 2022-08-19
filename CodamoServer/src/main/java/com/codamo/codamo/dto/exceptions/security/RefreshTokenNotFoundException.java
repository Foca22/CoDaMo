package com.codamo.codamo.dto.exceptions.security;

public class RefreshTokenNotFoundException extends Exception {

    public RefreshTokenNotFoundException() {
        super("Refresh Token Not Found");
    }
}
