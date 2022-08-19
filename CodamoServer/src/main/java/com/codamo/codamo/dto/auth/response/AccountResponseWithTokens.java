package com.codamo.codamo.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponseWithTokens {
    private AuthorizationResponse tokens;
    private AccountResponse account;
}
