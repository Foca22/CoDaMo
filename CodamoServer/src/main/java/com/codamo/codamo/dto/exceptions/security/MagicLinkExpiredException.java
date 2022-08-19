package com.codamo.codamo.dto.exceptions.security;

public class MagicLinkExpiredException extends Exception {

    public MagicLinkExpiredException() {
        super("Magic Link Expired");
    }
}
