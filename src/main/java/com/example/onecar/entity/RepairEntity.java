package com.example.onecar.entity;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.RepairTimeline;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Entity
@Table(name = "repairs")
public class RepairEntity extends BaseEntity {

    private Date started;

    private Date ended;

    private RepairCycle repairCycle;

    private RepairTimeline repairTimeline;

    @Column(name = "customer_id")
    private Long customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private UserEntity customer;

    @Override
    protected RepairDto toDto() {
        RepairDto dto = new RepairDto();
        BeanUtils.copyProperties(this, dto, "customer");

        if(customer != null) {
            dto.setCustomer(getCustomer().toDto());
        }

        return dto;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public RepairCycle getRepairCycle() {
        return repairCycle;
    }

    public void setRepairCycle(RepairCycle repairCycle) {
        this.repairCycle = repairCycle;
    }

    public RepairTimeline getRepairTimeline() {
        return repairTimeline;
    }

    public void setRepairTimeline(RepairTimeline repairTimeline) {
        this.repairTimeline = repairTimeline;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }
}
