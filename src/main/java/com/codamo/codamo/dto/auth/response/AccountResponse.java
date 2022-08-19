package com.codamo.codamo.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean admin;
}
