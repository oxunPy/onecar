package com.example.onecar.entity.base;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    MECHANIC,
    MANAGER,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }


}
