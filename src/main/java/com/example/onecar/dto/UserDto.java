package com.example.onecar.dto;

import com.example.onecar.entity.base.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UserDto extends BaseDto {

    @NotBlank(message = "Firstname should not be null or empty")
    private String firstName;

    @NotBlank(message = "Lastname should not be null or empty")
    private String lastName;

    @NotBlank(message = "Middlename should not be null or empty")
    private String middleName;

    @NotBlank(message = "Phone should not be null or empty")
    private String phone;

    private String email;

    @NotNull(message = "User type cannot be null")
    private UserType userType;

    @NotBlank(message = "Password should not be null or empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    Set<CarDto> myCars;

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<CarDto> getMyCars() {
        return myCars;
    }

    public void setMyCars(Set<CarDto> myCars) {
        this.myCars = myCars;
    }
}
