package com.example.onecar.entity;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.RepairTimeline;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "repairs")
public class RepairEntity extends BaseEntity {

    private Date started;

    private Date ended;

    private RepairCycle repairCycle;

    private RepairTimeline repairTimeline;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private UserEntity customer;

    @Column(name = "car_id", nullable = false)
    private Long carId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", insertable = false, updatable = false)
    private CarEntity car;

    private String demand;

    @Transient
    List<RepairDetailEntity> repairDetails = new ArrayList<>();

    @Override
    public RepairDto toDto() {
        RepairDto dto = new RepairDto();
        BeanUtils.copyProperties(this, dto, "customer");

        if(customer != null) {
            dto.setCustomer(getCustomer().toDto());
        }

        if(car != null) {
            dto.setCar(getCar().toDto());
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

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public List<RepairDetailEntity> getRepairDetails() {
        return repairDetails;
    }

    public void setRepairDetails(List<RepairDetailEntity> repairDetails) {
        this.repairDetails = repairDetails;
    }
}
