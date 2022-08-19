package com.codamo.codamo.controller;

import com.codamo.codamo.dto.auth.request.LoginRequest;
import com.codamo.codamo.dto.auth.request.RegisterRequest;
import com.codamo.codamo.dto.exceptions.security.*;
import com.codamo.codamo.service.external.AuthenticationService;
import com.codamo.codamo.dto.exceptions.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService accountService;

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyExistsException, PasswordsNotMatchException {
        return ResponseEntity.ok(accountService.register(registerRequest));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws EmailNotFoundException, WrongPasswordException {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

    @PostMapping("/emailVerify/{token}")
    ResponseEntity<?> verifyEmail(@PathVariable("token") String token) throws EmailNotFoundException, InvalidMagicLinkException, MagicLinkExpiredException {
        try {
            accountService.verifyEmail(token);
        } catch (EmailVerificationExpiredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EmailNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh/{token}")
    ResponseEntity<?> refreshToken(@PathVariable("token") String token) throws EmailNotFoundException, InvalidMagicLinkException, MagicLinkExpiredException, RefreshTokenExpiredException, RefreshTokenNotFoundException {
        return ResponseEntity.ok(accountService.refreshToken(token));
    }

    @GetMapping()
    ResponseEntity<?> getAccount() throws EmailNotFoundException, InvalidMagicLinkException, MagicLinkExpiredException, RefreshTokenExpiredException, RefreshTokenNotFoundException, AccountNotFoundException {
        return ResponseEntity.ok(accountService.getAccount());
    }
}
