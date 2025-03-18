package com.example.onecar.entity;

import com.example.onecar.dto.UserDto;
import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.UserType;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private String middleName;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Transient
    private Set<CarEntity> myCars;

    @Override
    protected UserDto toDto() {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userType);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
    }
}
