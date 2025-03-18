package com.example.onecar.dto;

import com.example.onecar.entity.base.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto {
    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    private UserType userType;
}
