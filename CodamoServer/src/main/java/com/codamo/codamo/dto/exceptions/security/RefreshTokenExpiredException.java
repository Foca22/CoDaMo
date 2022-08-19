package com.codamo.codamo.dto.exceptions.security;

public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException() {
        super("Refresh Token Expired");
    }
}
