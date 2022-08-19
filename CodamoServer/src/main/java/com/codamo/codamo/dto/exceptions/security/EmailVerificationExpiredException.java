package com.codamo.codamo.dto.exceptions.security;

public class EmailVerificationExpiredException extends Exception {

    public EmailVerificationExpiredException() {
        super("Email Verification Expired");
    }
}
