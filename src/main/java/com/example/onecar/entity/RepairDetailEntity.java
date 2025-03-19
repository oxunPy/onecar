package com.example.onecar.entity;

import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.entity.base.BaseEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity
@Table(name = "repair_details")
public class RepairDetailEntity extends BaseEntity {

    private BigDecimal price;

    private String serviceType;      // оказанный услуг

    private String bodyPart;         // Запчасть

    @Column(name = "repair_id", nullable = false)
    private Long repairId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id", insertable = false, updatable = false)
    private RepairEntity repair;

    @Column(name = "mechanic_id")
    private Long mechanicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", insertable = false, updatable = false)
    private UserEntity mechanic;

    @Override
    public RepairDetailDto toDto() {
        RepairDetailDto dto = new RepairDetailDto();
        BeanUtils.copyProperties(this, dto, "repair", "mechanic");

        if(repair != null) {
            dto.setRepair(getRepair().toDto());
        }

        if(mechanic != null) {
            dto.setMechanic(getMechanic().toDto());
        }

        return dto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public RepairEntity getRepair() {
        return repair;
    }

    public void setRepair(RepairEntity repair) {
        this.repair = repair;
    }

    public Long getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(Long mechanicId) {
        this.mechanicId = mechanicId;
    }

    public UserEntity getMechanic() {
        return mechanic;
    }

    public void setMechanic(UserEntity mechanic) {
        this.mechanic = mechanic;
    }
}
