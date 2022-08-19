package com.codamo.codamo.service.internal.impl;

import com.codamo.codamo.dto.auth.request.CreateTokenRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.model.auth.Account;
import com.codamo.codamo.model.auth.RefreshToken;
import com.codamo.codamo.repo.account.RefreshTokenRepo;
import com.codamo.codamo.security.AuthoritiesConstants;
import com.codamo.codamo.security.jwt.JwtData;
import com.codamo.codamo.security.jwt.TokenProvider;
import com.codamo.codamo.service.internal.AccountInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountInternalServiceImpl implements AccountInternalService {

    public BCryptPasswordEncoder getPasswordEncoder() {
        return bCryptPasswordEncoder();
    }

    @Autowired
    RefreshTokenRepo refreshTokenRepo;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getAuthorities().contains(AuthoritiesConstants.ADMIN)
        );
    }

    public AuthorizationResponse buildTokens(Account account) {
        String jwt = generateJwtToken(account);
        RefreshToken refreshToken = generateRefreshToken(account);

        return new AuthorizationResponse(jwt, refreshToken.getToken());
    }

    private String generateJwtToken(Account account) {
        List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(account.getEmail(), null, grantedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtData jwtData = new JwtData(account.getId(), account.getFirstName(), account.getLastName(), account.getEmail());
        CreateTokenRequest createTokenRequest = new CreateTokenRequest(authentication, jwtData);
        return tokenProvider.createJwtToken(createTokenRequest);
    }

    private RefreshToken generateRefreshToken(Account account) {
        RefreshToken refreshToken = account.getRefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiry(Instant.now().plus(30, ChronoUnit.DAYS));
        return refreshTokenRepo.save(refreshToken);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
