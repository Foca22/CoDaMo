package com.codamo.codamo.service.internal;

import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.model.auth.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * External service used only by other services
 */
public interface AccountInternalService {

    BCryptPasswordEncoder getPasswordEncoder();

    AccountResponse toAccountResponse(Account account);

    AuthorizationResponse buildTokens(Account account);
}
