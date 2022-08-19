package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.auth.request.RegisterRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.auth.response.AccountResponseWithTokens;
import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.dto.exceptions.security.AccountNotFoundException;
import com.codamo.codamo.dto.exceptions.security.EmailAlreadyExistsException;
import com.codamo.codamo.dto.exceptions.security.PasswordsNotMatchException;
import com.codamo.codamo.model.auth.Account;
import com.codamo.codamo.model.auth.EmailVerification;
import com.codamo.codamo.model.auth.RefreshToken;
import com.codamo.codamo.repo.account.AccountRepo;
import com.codamo.codamo.repo.account.EmailVerificationRepo;
import com.codamo.codamo.repo.account.RefreshTokenRepo;
import com.codamo.codamo.security.AuthoritiesConstants;
import com.codamo.codamo.service.internal.AccountInternalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    /**
     * The class that we want to test. We are injecting mocks into it
     */
    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    /**
     * A mock. We describe what we want to do with it in code
     */
    @Mock
    AccountRepo accountRepo;

    @Mock
    RefreshTokenRepo refreshTokenRepo;

    @Mock
    private EmailVerificationRepo emailVerificationRepo;

    @Mock
    private AccountInternalService accountInternalService;

    private static final String FIRST_NAME = "Joe";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "joe.doe@example.com";
    private static final String PASSWORD = "pass123";
    private static final String REFRESH_TOKEN_ID = UUID.randomUUID().toString();
    private static final String EMAIL_VERIFICATION_ID = UUID.randomUUID().toString();
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();
    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    /**
     * This method will run before each and every tests
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldRegisterUser() throws PasswordsNotMatchException, EmailAlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PASSWORD);

        // When it tries to save a refresh token (any refresh token), we are intercepting the call, we get the argument passed to the
        // .save method, which is a RefreshToken, we append to that refresh token our own ID (simulating the database's id generation),
        // and return that RefreshToken with the new ID
        when(refreshTokenRepo.save(any(RefreshToken.class))).thenAnswer(invocation -> {
            RefreshToken refreshToken = (RefreshToken) invocation.getArguments()[0];
            refreshToken.setId(REFRESH_TOKEN_ID);
            return refreshToken;
        });

        when(emailVerificationRepo.save(any(EmailVerification.class))).thenAnswer(invocation -> {
            EmailVerification emailVerification = (EmailVerification) invocation.getArguments()[0];
            emailVerification.setId(EMAIL_VERIFICATION_ID);
            return emailVerification;
        });

        // When it tries to see if an account exists by email, we are returning directly false
        when(accountRepo.existsByEmail(any())).thenReturn(false);

        when(accountRepo.save(any(Account.class))).thenAnswer(invocation -> {
            Account account = (Account) invocation.getArguments()[0];
            account.setId(ACCOUNT_ID);
            return account;
        });

        when(accountInternalService.buildTokens(any(Account.class))).thenReturn(new AuthorizationResponse(ACCESS_TOKEN, REFRESH_TOKEN));
        when(accountInternalService.getPasswordEncoder()).thenReturn(new BCryptPasswordEncoder());

        when(accountInternalService.toAccountResponse(any(Account.class))).thenAnswer(invocation -> {
            Account account = (Account) invocation.getArguments()[0];
            return new AccountResponse(account.getId(), account.getFirstName(), account.getLastName(), account.getEmail(), account.getAuthorities().contains(AuthoritiesConstants.ADMIN));
        });

        AccountResponseWithTokens response = authenticationService.register(registerRequest);

        // We are verifying the output of the method that we are testing
        assertEquals("", ACCOUNT_ID, response.getAccount().getId());
        assertEquals("", FIRST_NAME, response.getAccount().getFirstName());
        assertEquals("", LAST_NAME, response.getAccount().getLastName());
        assertEquals("", EMAIL, response.getAccount().getEmail());
        assertEquals("", ACCESS_TOKEN, response.getTokens().getAccessToken());
        assertEquals("", REFRESH_TOKEN, response.getTokens().getRefreshToken());
    }

    @Test
    public void shouldNotRegisterUserIfEmailAlreadyExists() {
        RegisterRequest registerRequest = new RegisterRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PASSWORD);

        when(accountRepo.existsByEmail(any())).thenReturn(true);

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> authenticationService.register(registerRequest));

        String expectedMessage = "Email " + EMAIL + " already exists.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotRegisterUserIfPasswordDoNotMatch() {
        RegisterRequest registerRequest = new RegisterRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, "other password");

        when(accountRepo.existsByEmail(any())).thenReturn(false);

        assertThrows(PasswordsNotMatchException.class, () -> authenticationService.register(registerRequest));
    }

    @Test
    public void shouldGetAccount() throws AccountNotFoundException {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(authentication.getPrincipal()).thenReturn(new org.springframework.security.core.userdetails.User(String.format("{\"id\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"email\":\"%s\"}", ACCOUNT_ID, FIRST_NAME, LAST_NAME, EMAIL), "", new ArrayList<>()));

        when(accountRepo.findById(ACCOUNT_ID)).thenReturn(Optional.of(new Account(ACCOUNT_ID, EMAIL, "", FIRST_NAME, LAST_NAME, FIRST_NAME + LAST_NAME, null, null, null, true, List.of(AuthoritiesConstants.ADMIN))));

        AccountResponse response = authenticationService.getAccount();

        assertEquals("", ACCOUNT_ID, response.getId());
        assertEquals("", FIRST_NAME, response.getFirstName());
        assertEquals("", LAST_NAME, response.getLastName());
        assertEquals("", EMAIL, response.getEmail());
        assertEquals("", true, response.isAdmin());
    }

    @Test
    public void shouldNotGetAccountIfAccountNotFound() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(authentication.getPrincipal()).thenReturn(new org.springframework.security.core.userdetails.User(String.format("{\"id\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"email\":\"%s\"}", ACCOUNT_ID, FIRST_NAME, LAST_NAME, EMAIL), "", new ArrayList<>()));

        when(accountRepo.findById(ACCOUNT_ID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(AccountNotFoundException.class, () -> authenticationService.getAccount());

        String expectedMessage = "Account with ID " + ACCOUNT_ID + " not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}