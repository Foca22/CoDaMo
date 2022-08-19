package com.codamo.codamo.dto.auth.request;

import com.codamo.codamo.security.jwt.JwtData;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@AllArgsConstructor
public class CreateTokenRequest {
    Authentication authentication;
    JwtData jwtData;
}
