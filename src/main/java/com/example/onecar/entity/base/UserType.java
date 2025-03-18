package com.example.onecar.entity.base;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    MECHANIC,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
