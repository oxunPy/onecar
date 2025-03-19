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
import java.util.HashSet;
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
    public UserDto toDto() {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(this, dto);

        if(myCars != null) {
            dto.setMyCars(new HashSet<>());
            for(CarEntity car : myCars) {
                dto.getMyCars().add(car.toDto());
            }
        }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<CarEntity> getMyCars() {
        return myCars;
    }

    public void setMyCars(Set<CarEntity> myCars) {
        this.myCars = myCars;
    }
}
