package com.example.onecar.entity;

import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    @Column(name = "user_type")
    private UserType userType;
}
