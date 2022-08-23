package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.exceptions.security.*;
import com.codamo.codamo.utils.SecurityUtils;
import com.codamo.codamo.dto.auth.request.LoginRequest;
import com.codamo.codamo.dto.auth.request.RegisterRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.auth.response.AccountResponseWithTokens;
import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.dto.exceptions.security.*;
import com.codamo.codamo.model.auth.Account;
import com.codamo.codamo.model.auth.EmailVerification;
import com.codamo.codamo.model.auth.RefreshToken;
import com.codamo.codamo.repo.account.AccountRepo;
import com.codamo.codamo.repo.account.EmailVerificationRepo;
import com.codamo.codamo.repo.account.RefreshTokenRepo;
import com.codamo.codamo.security.AuthoritiesConstants;
import com.codamo.codamo.service.external.AuthenticationService;
import com.codamo.codamo.service.internal.AccountInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AccountRepo accountRepo;

    private final RefreshTokenRepo refreshTokenRepo;

    private final EmailVerificationRepo emailVerificationRepo;

    private final AccountInternalService accountInternalService;

    @Autowired
    public AuthenticationServiceImpl(AccountRepo accountRepo, RefreshTokenRepo refreshTokenRepo,
                                     EmailVerificationRepo emailVerificationRepo, AccountInternalService accountInternalService) {
        this.accountRepo = accountRepo;
        this.refreshTokenRepo = refreshTokenRepo;
        this.emailVerificationRepo = emailVerificationRepo;
        this.accountInternalService = accountInternalService;
    }
//    @Autowired
//    private MailService mailService;

    @Override
    @Transactional
    public AccountResponseWithTokens register(RegisterRequest registerRequest) throws EmailAlreadyExistsException, PasswordsNotMatchException {
        String email = registerRequest.getEmail().toLowerCase().trim();

        if (accountRepo.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email " + email + " already exists.");
        }

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new PasswordsNotMatchException();
        }

        Account account = createAccount(registerRequest);
        RefreshToken refreshToken = createRefreshToken(account);
        account.setRefreshToken(refreshToken);
        EmailVerification emailVerification = createEmailVerification(account);
        account.setEmailVerification(emailVerification);

        account = accountRepo.save(account);

//        mailService.sendMagicLink(new MagicLinkEmailDto(account.getFirstName(), account.getEmail(), magicLink.getToken()));

        return new AccountResponseWithTokens(
                accountInternalService.buildTokens(account),
                accountInternalService.toAccountResponse(account)
        );
    }

    @Override
    public AccountResponseWithTokens login(LoginRequest loginRequest) throws EmailNotFoundException, WrongPasswordException {
        String email = loginRequest.getEmail();

        Optional<Account> accountOptional = accountRepo.findByEmail(email);

        if (accountOptional.isEmpty()) {
            throw new EmailNotFoundException("Email " + email + " not found.");
        }


        Account account = accountOptional.get();

        if (!accountInternalService.getPasswordEncoder().matches(loginRequest.getPassword(), account.getPassword())) {
            throw new WrongPasswordException();
        }

        return new AccountResponseWithTokens(
                accountInternalService.buildTokens(account),
                accountInternalService.toAccountResponse(account)
        );
    }

    @Override
    public void verifyEmail(String token) throws EmailNotFoundException, EmailVerificationExpiredException {

        Optional<EmailVerification> emailVerificationOptional = emailVerificationRepo.findByToken(token);
        if (emailVerificationOptional.isEmpty()) {
            throw new EmailNotFoundException("Email not found.");
        }

        EmailVerification emailVerification = emailVerificationOptional.get();

        if (emailVerification.getExpiry().isAfter(Instant.now())) {
            throw new EmailVerificationExpiredException();
        }

        Account account = emailVerification.getAccount();
        account.setVerified(true);

        accountRepo.save(account);
    }

    @Override
    public AuthorizationResponse refreshToken(String token) throws RefreshTokenNotFoundException, RefreshTokenExpiredException {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepo.findByToken(token);

        if (refreshTokenOptional.isEmpty()) {
            throw new RefreshTokenNotFoundException();
        }

        RefreshToken refreshToken = refreshTokenOptional.get();

        if (refreshToken.getExpiry().isBefore(Instant.now())) {
            throw new RefreshTokenExpiredException();
        }
        Account account = refreshToken.getAccount();

        return accountInternalService.buildTokens(account);
    }

    @Override
    public AccountResponse getAccount() throws AccountNotFoundException {
        String userId = SecurityUtils.getUserId();
        Account account = accountRepo.findById(userId).orElseThrow(() -> new AccountNotFoundException("Account with ID " + userId + " not found."));
        return new AccountResponse(
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getAuthorities().contains(AuthoritiesConstants.ADMIN)
        );
    }

    private RefreshToken createRefreshToken(Account account) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(account);

        return refreshTokenRepo.save(refreshToken);
    }

    private EmailVerification createEmailVerification(Account account) {
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setAccount(account);

        return generateEmailVerification(emailVerification);
    }

    private Account createAccount(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setAuthorities(List.of(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN));
        account.setEmail(registerRequest.getEmail());
        account.setFirstName(registerRequest.getFirstName());
        account.setLastName(registerRequest.getLastName());
        account.setFullName(account.getFirstName() + " " + account.getLastName());
        account.setPassword(accountInternalService.getPasswordEncoder().encode(registerRequest.getPassword()));

        return account;
    }

    private EmailVerification generateEmailVerification(EmailVerification emailVerification) {
        int EMAIL_VERIFICATION_SECONDS = 60 * 60;
        emailVerification.setExpiry(Instant.now().plusSeconds(EMAIL_VERIFICATION_SECONDS));
        emailVerification.setToken(UUID.randomUUID().toString());

        return emailVerificationRepo.save(emailVerification);
    }

}
