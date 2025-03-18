package com.example.onecar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto extends BaseDto {
    private String mark;

    private String model;

    private String vinNumber;

    private Integer releaseYear;
}
