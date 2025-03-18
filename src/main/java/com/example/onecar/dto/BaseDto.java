package com.example.onecar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDto {
    private Long id;

    private Date createdDate;
}
