package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.auth.request.LoginRequest;
import com.codamo.codamo.dto.auth.request.RegisterRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.auth.response.AccountResponseWithTokens;
import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.dto.exceptions.security.*;
import com.codamo.codamo.dto.exceptions.security.*;

public interface AuthenticationService {

    AccountResponseWithTokens register(RegisterRequest registerRequest) throws EmailAlreadyExistsException, PasswordsNotMatchException;

    AccountResponseWithTokens login(LoginRequest loginRequest) throws EmailNotFoundException, WrongPasswordException;

    void verifyEmail(String token) throws EmailNotFoundException, EmailVerificationExpiredException;

    AuthorizationResponse refreshToken(String refreshToken) throws RefreshTokenNotFoundException, RefreshTokenExpiredException;

    AccountResponse getAccount() throws AccountNotFoundException;
}
