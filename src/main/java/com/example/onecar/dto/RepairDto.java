package com.example.onecar.dto;

import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.RepairTimeline;

import java.util.Date;

public class RepairDto extends BaseDto {
    private Date started;

    private Date ended;

    private RepairCycle repairCycle;

    private RepairTimeline repairTimeline;

    private UserDto customer;

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
}
