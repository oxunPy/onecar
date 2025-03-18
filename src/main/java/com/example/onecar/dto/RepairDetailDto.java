package com.example.onecar.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RepairDetailDto extends BaseDto {
    private BigDecimal price;

    private String serviceType;      // оказанный услуг

    private String bodyPart;         // Запчасть

    private RepairDto repair;

    private UserDto mechanic;
}
