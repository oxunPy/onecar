package com.example.onecar.dto;

import jakarta.validation.constraints.NotBlank;

public class CarDto extends BaseDto {
    @NotBlank(message = "Car mark should not be empty!")
    private String mark;

    @NotBlank(message = "Car model should not be empty!")
    private String model;

    @NotBlank(message = "Car vin number should not be empty!")
    private String vinNumber;

    private Integer releaseYear;

    private UserDto owner;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }
}
