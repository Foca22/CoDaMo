package com.codamo.codamo.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtData {
    String id;
    String firstName;
    String lastName;
    String email;
}
