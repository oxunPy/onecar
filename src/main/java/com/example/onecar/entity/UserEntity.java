package com.example.onecar.entity;

import com.example.onecar.dto.UserDto;
import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.UserType;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    @Column(name = "user_type")
    private UserType userType;

    @Transient
    private Set<CarEntity> myCars;

    @Override
    protected UserDto toDto() {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
