package com.example.onecar.dto;

import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.RepairTimeline;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class  RepairDto extends BaseDto {
    private Date started;

    private Date ended;

    private RepairCycle repairCycle;

    @NotNull(message = "Customer time should not empty!")
    private RepairTimeline repairTimeline;

    private UserDto customer;

    @NotNull(message = "Repairable car should not empty!")
    private CarDto car;

    @NotBlank(message = "Customer demand should not empty!")
    private String demand;

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

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }
}
