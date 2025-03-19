package com.example.onecar.config.security;


import com.example.onecar.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserEntity getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserEntity user) {
            return user;
        }

        return null;
    }
}
