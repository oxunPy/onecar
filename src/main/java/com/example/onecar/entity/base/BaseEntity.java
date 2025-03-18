package com.example.onecar.entity.base;

import com.example.onecar.dto.BaseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BaseStatus status;

    private Date createdDate;

    protected abstract <T extends BaseDto> T toDto();
}
