package com.codamo.codamo.utils;

import com.codamo.codamo.security.jwt.JwtData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class SecurityUtils {

    public static String getUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((JwtData) JsonConverterUtils.convertFromJson(user.getUsername(), JwtData.class)).getId();
    }
}
