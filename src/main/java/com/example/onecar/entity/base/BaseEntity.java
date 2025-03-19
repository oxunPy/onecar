package com.example.onecar.entity.base;

import com.example.onecar.dto.BaseDto;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BaseStatus status;

    private Date createdDate;

    protected abstract <T extends BaseDto> T toDto();

    public void forCreate() {
        createdDate = new Date();
        status = BaseStatus.CREATED;
    }

    public void forActive() {
        status = BaseStatus.ACTIVE;
    }

    public void forPassive() {
        status = BaseStatus.PASSIVE;
    }

    public void forDelete() {
        status = BaseStatus.DELETED;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
