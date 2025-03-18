package com.example.onecar.entity;

import com.example.onecar.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    private String mark;

    private String model;

    private String vinNumber;

    private Integer releaseYear;
}
