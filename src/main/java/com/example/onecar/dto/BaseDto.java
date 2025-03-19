package com.example.onecar.dto;

import com.example.onecar.entity.base.BaseStatus;

import java.util.Date;

public class BaseDto {
    private Long id;

    private Date createdDate;

    private BaseStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
